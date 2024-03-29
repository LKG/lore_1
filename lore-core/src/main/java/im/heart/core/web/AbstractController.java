package im.heart.core.web;

import im.heart.core.CommonConst.RequestResult;
import im.heart.core.support.BigDecimalEditorSupport;
import im.heart.core.support.DateEditorSupport;
import im.heart.core.support.StringEscapeEditorSupport;
import im.heart.core.utils.BaseUtils;
import im.heart.core.validator.ValidatorUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author gg
 * @desc 基础控制器，提供日志记录，记录查询条件，文件上传功能 等一些共用方法
 */
public abstract  class AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);
    protected static final String RESULT_PAGE = RequestResult.PAGE;
    protected static final String VIEW_SUCCESS = RequestResult.PAGE_SUCCESS;
    private String viewPrefix;

    /**
     *
     *  当前模块 视图的前缀 默认 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     * @param viewPrefix
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }
    /**
     *
     * 设置session
     * @param request
     * @param paraName
     * @param para
     */
    protected void setSessionPara(HttpServletRequest request, String paraName, Object para) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(paraName, para);
        }
    }
    /**
     *
     * 根据参数名获取SESSION 对应值
     * @param request
     * @param paraName
     * @return
     */
    protected Object getSessionPara(HttpServletRequest request, String paraName) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object para = session.getAttribute(paraName);
            if (para != null) {
                return para;
            }
        }
        return null;
    }
    /**
     *
     * backURL null 将重定向到默认getViewPrefix()
     * @param backURL
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isBlank(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http") && !backURL.startsWith("https")) {
            backURL = "/" + backURL;
        }
        return "redirect:" + backURL;
    }
    /**
     * 获取当前请求根路径
     * @param req
     * @return
     */
    protected String getBasePath(HttpServletRequest req) {
        return BaseUtils.getBasePath(req);
    }
    /**
     * 获取上次请求地址
     * @param request
     * @return
     */
    protected String extractBackURL(HttpServletRequest request) {
        return BaseUtils.extractBackURL(request);
    }
    /**
     *
     * 获取上传文件列表信息 ？@RequestParam MultipartFile file[],
     * @param request
     * @return
     */
    protected List<MultipartFile> getFileList(HttpServletRequest request) {
        List<MultipartFile> fileList = new ArrayList<MultipartFile>();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
                String key =  it.next();
                MultipartFile file = multipartRequest.getFile(key);
                if (file.getOriginalFilename().length() > 0) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }
    public String getViewPrefix() {
        return viewPrefix;
    }
    protected void success(ModelMap model) {
        success(model, true);
    }
    protected void success(ModelMap model, Object attributeValue) {
        this.success(model, RequestResult.RESULT, attributeValue);
    }
    protected void success(ModelMap model, String dataKey, Object attributeValue) {
        model.put(RequestResult.SUCCESS, true);
        model.put(RequestResult.HTTP_STATUS, HttpStatus.OK.value());
        model.put(dataKey, attributeValue);
    }

    protected void fail(ModelMap model) {
        this.fail(model,false);
    }
    protected void fail(ModelMap model, Object attributeValue) {
        this.fail(model, RequestResult.RESULT, attributeValue);
    }
    protected void fail(ModelMap model, String dataKey, Object attributeValue) {
        model.put(RequestResult.SUCCESS, false);
        model.put(RequestResult.HTTP_STATUS, HttpStatus.OK.value());
        model.put(dataKey, attributeValue);
    }
    protected void error(ModelMap model, String dataKey, Object attributeValue) {
        this.fail(model, dataKey, attributeValue);
    }
    protected void error(ModelMap model, Object attributeValue) {
        this.error(model, RequestResult.RESULT, attributeValue);
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 处理BigDecimal
        binder.registerCustomEditor(BigDecimal.class, new BigDecimalEditorSupport());
        // 处理Date
        binder.registerCustomEditor(Date.class, new DateEditorSupport());
        //处理boolean 类型
        binder.registerCustomEditor(Boolean.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if(StringUtils.isNotBlank(text)){
                    setValue(Boolean.valueOf(text));
                }
            }
        });
        // 用于防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEscapeEditorSupport());
        //  注册共用校验器
    }
    /**
     *
     * 文件上传
     * @param file
     * @param path
     * @return
     * @throws Exception
     */
    protected String uploadFile(MultipartFile file, String path,String fileName) throws Exception {
        FileOutputStream out = null;
        try {
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if(StringUtils.isBlank(fileName) ){
                fileName = file.getOriginalFilename();
                boolean isCN = ValidatorUtils.isContainsChinese(fileName);
                if(isCN){
                    //获取文件名称
                    String oldFileName = StringUtils.substringBeforeLast(fileName, ".");
                    //获取文件后缀
                    String suffixes = StringUtils.substringAfterLast(fileName, ".");
                    fileName=Base64.encodeBase64URLSafeString(oldFileName.getBytes())+"."+suffixes;
                }
            }
            fileName = System.nanoTime()+ "_"+fileName;
            String filePath = path + File.separator + fileName;
            out = new FileOutputStream(filePath);
            out.write(file.getBytes());
            return fileName;
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    /**
     *
     * 文件上传
     * @param file
     * @param path
     * @return
     * @throws Exception
     */
    protected String uploadFile(MultipartFile file, String path) throws Exception {
        return uploadFile(file,path,null);
    }
}
