package im.heart.frame.web;

import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.utils.StringUtilsEx;
import im.heart.core.validator.ValidatorUtils;
import im.heart.core.web.AbstractController;
import im.heart.frame.entity.FrameArea;
import im.heart.frame.entity.FrameDict;
import im.heart.frame.service.FrameAreaService;
import im.heart.frame.validator.FrameAreaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @desc 区域管理表控制器
 */
@Controller
public class TestController extends AbstractController {
	
	protected static final String apiVer = "/index";
	protected static final String VIEW_LIST="admin/frame/area_list";
	@Autowired
	private FrameAreaService frameAreaService;
	@RequestMapping(value ={apiVer+"","/"} )
	public ModelAndView createFrom(HttpServletRequest request, HttpServletResponse response,
								   @RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
								   @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
								   @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
								   @RequestParam(value = "sort", required = false ,defaultValue="code") String sort,
								   @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
								   @RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
								   ModelMap model) {
		System.out.println("@@@@@@@@@@@@@list@@@@@@@@@@@@@@@@@@@@");
		Specification<FrameArea> spec=DynamicSpecifications.bySearchFilter(request, FrameArea.class);
		PageRequest pageRequest=DynamicPageRequest.buildPageRequest(page,size,sort,order,FrameArea.class);
		Page<FrameArea> pag = this.frameAreaService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
