package im.heart.material.web;

import im.heart.core.CommonConst;
import im.heart.core.utils.DateUtilsEx;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.web.AbstractController;
import im.heart.core.web.ResponseError;
import im.heart.core.web.enums.WebError;
import im.heart.material.entity.MaterialPeriodical;
import im.heart.material.parser.MaterialPeriodicalParser;
import im.heart.material.service.MaterialPeriodicalService;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.vo.FrameUserVO;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Controller
public class UploadMaterialController extends AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(UploadMaterialController.class);
    protected static final String apiVer = "/upload";
    protected static final String FILE_ROOT_PATH = CommonConst.STATIC_UPLOAD_ROOT;

    @Value("${prod.material.file.path}")
    private String materialFilePath = "";
    @Value("${prod.upload.path.root}")
    private String uploadFilePath = "";

    @Autowired
    private MaterialPeriodicalService materialPeriodicalService;

    @Autowired
    private MaterialPeriodicalParser materialPeriodicalParser;

    /**
     * 文件上传
     *
     * @param file
     * @param path
     * @return
     * @throws Exception
     */
    @Override
    protected String uploadFile(MultipartFile file, String path) throws Exception {
        return super.uploadFile(file, path, null);
    }

    @RequestMapping(apiVer + "/material")
    public ModelAndView importBathMaterialImg(HttpServletRequest request,
                                              @RequestParam(value = "jsoncallback", required = false) String jsoncallback,
                                              @RequestParam(value = "periodicalCode", required = false) String periodicalCode,
                                              @RequestParam(value = "categoryId", required = false) BigInteger categoryId,
                                              @RequestParam(value = "categoryCode", required = false) String categoryCode,
                                              @RequestParam(value = "originPrice", required = false) BigDecimal originPrice,
                                              @RequestParam(value = "finalPrice", required = false) BigDecimal finalPrice,
                                              String filename,
                                              HttpServletResponse response, ModelMap model) {
        ResponseError responseError = new ResponseError(WebError.AUTH_CREDENTIALS_EXPIRED);
        FrameUserVO user = SecurityUtilsHelper.getCurrentUser();
        if (user == null) {
            responseError = new ResponseError(WebError.INVALID_REQUEST);
            super.fail(model, responseError);
            return new ModelAndView(RESULT_PAGE);
        }
        List<MultipartFile> uploadFileList = super.getFileList(request);
        if (uploadFileList != null && !uploadFileList.isEmpty()) {
            for (MultipartFile file : uploadFileList) {
                String path = File.separator + materialFilePath + File.separator + DateTime.now().toString("yyyyMMdd") + File.separator;
                try {
                    String realPath = uploadFilePath + path;
                    String realFileName = this.uploadFile(file, realPath);
                    if (StringUtilsEx.isBlank(filename)) {
                        filename = file.getOriginalFilename();
                    }
                    MaterialPeriodical periodical = new MaterialPeriodical();
                    periodical.setRealFilePath(realPath + realFileName);
                    String suffixes = StringUtils.substringAfterLast(realFileName, ".");
                    periodical.setFileHeader(suffixes);
                    periodical.setPeriodicalType(MaterialPeriodical.PeriodicalType.sharing.code);
                    periodical.setAuthor(user.getNickName());
                    periodical.setUserId(user.getUserId());
                    periodical.setCategoryId(categoryId);
                    periodical.setCategoryCode(categoryCode);
//                    periodical.setCityId(cityId);
//                    periodical.setCityName(cityName);
                    periodical.setPeriodicalName(filename);
                    periodical.setPeriodicalCode(periodicalCode);
                    periodical.setFinalPrice(finalPrice);
                    periodical.setOriginPrice(originPrice);
                    periodical.setDataSize(file.getSize());
                    periodical.setStatus(CommonConst.FlowStatus.INITIAL);
                    periodical.setImportLog(DateUtilsEx.timeToString(new Date()) + " ,上传成功！<br/>");
                    String url = StringUtilsEx.replace(path + realFileName, File.separator, "/");
                    String pathUrl = "/" + FILE_ROOT_PATH + "/" + url;
                    periodical.setPathUrl(pathUrl);
                    this.materialPeriodicalService.save(periodical);
                    this.materialPeriodicalParser.addParserTask(periodical, file.getInputStream());
                    super.success(model, "url", pathUrl);
                } catch (Exception e) {
                    logger.error(e.getStackTrace()[0].getMethodName(), e);
                    super.fail(model, new ResponseError(WebError.REQUEST_EXCEPTION));
                    return new ModelAndView(RESULT_PAGE);
                } finally {

                }
                return new ModelAndView(VIEW_SUCCESS);
            }
            super.fail(model, responseError);
            return new ModelAndView(RESULT_PAGE);
        }
        super.fail(model, responseError);
        return new ModelAndView(RESULT_PAGE);
    }

}
