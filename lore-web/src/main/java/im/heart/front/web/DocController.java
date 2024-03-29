package im.heart.front.web;

import com.google.common.collect.Lists;
import im.heart.core.CommonConst;
import im.heart.core.enums.Status;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.web.AbstractController;
import im.heart.material.entity.MaterialPeriodical;
import im.heart.material.service.MaterialPeriodicalImgService;
import im.heart.material.service.MaterialPeriodicalService;
import im.heart.material.vo.MaterialPeriodicalVO;
import im.heart.security.utils.SecurityUtilsHelper;
import im.heart.usercore.entity.FrameUserFollow;
import im.heart.usercore.service.FrameUserFollowService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Controller
public class DocController extends AbstractController {
    protected static final String apiVer = "/doc";
    protected static final String VIEW_LIST="front/doc/doc_list";
    protected static final String VIEW_DETAILS="front/doc/doc_details";
    protected static final String VIEW_CREATE="front/doc/doc_create";

    @Autowired
    private MaterialPeriodicalService materialPeriodicalService;

    @Autowired
    private FrameUserFollowService frameUserFollowService;

    @Autowired
    private MaterialPeriodicalImgService materialPeriodicalImgService;

    @RequestMapping(value = apiVer+"/{id}")
    protected ModelAndView findById(
            @RequestParam(value =CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = "access_token", required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        MaterialPeriodical po = this.materialPeriodicalService.findById(id);
        MaterialPeriodicalVO vo=new MaterialPeriodicalVO(po);
        super.success(model, vo);
        return new ModelAndView(VIEW_DETAILS);
    }
    @RequestMapping(apiVer+"s")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value =CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = "access_token", required = false) String token,
                             ModelMap model) {
        Specification<MaterialPeriodical> spec=DynamicSpecifications.bySearchFilter(request, MaterialPeriodical.class);
        PageRequest pageRequest=DynamicPageRequest.buildPageRequest(page,size,sort,order,MaterialPeriodical.class);
        Page<MaterialPeriodical> pag = this.materialPeriodicalService.findAll(spec, pageRequest);
        if(pag!=null&&pag.hasContent()){
            List<MaterialPeriodicalVO> vos = Lists.newArrayList();
            for(MaterialPeriodical po:pag.getContent()){
                vos.add(new MaterialPeriodicalVO(po));
            }
            Page<MaterialPeriodicalVO> docVos  =new PageImpl<MaterialPeriodicalVO>(vos,pageRequest,pag.getTotalElements());
            super.success(model,docVos);
            return new ModelAndView(VIEW_LIST);
        }
        super.success(model,pag);
        return new ModelAndView(VIEW_LIST);
    }

    @RequestMapping(value = apiVer+"/{id}/praise")
    protected ModelAndView praise(
            @RequestParam(value =CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = "access_token", required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
//        MaterialPeriodical po = this.materialPeriodicalService.findOne(id);
//        super.success(model, po);
        return new ModelAndView(VIEW_DETAILS);
    }
    @RequiresAuthentication
    @RequestMapping(value = apiVer+"/{id}/collect")
    protected ModelAndView collect(
            @RequestParam(value =CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
            @PathVariable BigInteger id,
            @RequestParam(value = "access_token", required = false) String token,
            HttpServletRequest request,
            ModelMap model) {
        BigInteger userId= SecurityUtilsHelper.getCurrentUser().getUserId();
        MaterialPeriodical materialPeriodical=this.materialPeriodicalService.findById(id);
        Optional<FrameUserFollow> optional= this.frameUserFollowService.findByUserIdAndRelateId(userId,id);
        if(!optional.isPresent()){
            FrameUserFollow  userFollow=new FrameUserFollow();
            userFollow.setRelateId(id);
            userFollow.setType(materialPeriodical.getPeriodicalType());
            userFollow.setUserId(userId);
            userFollow.setStatus(Status.enabled);
            userFollow.setItemTitle(materialPeriodical.getPeriodicalName());
            userFollow.setItemImgUrl(materialPeriodical.getCoverImgUrl());
            this.frameUserFollowService.save(userFollow);
        }
        super.success(model,true);
        return new ModelAndView(VIEW_DETAILS);
    }
}
