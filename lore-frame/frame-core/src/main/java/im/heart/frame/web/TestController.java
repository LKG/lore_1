package im.heart.frame.web;

import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.plugins.persistence.SearchFilter.Operator;
import im.heart.core.web.AbstractController;
import im.heart.frame.entity.FrameDict;
import im.heart.frame.entity.FrameDictItem;
import im.heart.frame.service.FrameDictItemService;
import im.heart.frame.service.FrameDictService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Collection;

/**
 * 
 * @author gg
 * @desc : 数据字典子表
 */
@Controller
public class TestController extends AbstractController {
	
	protected static final String apiVer = "/index";
	@Autowired
	private FrameDictService frameDictService;
	@Autowired
	private FrameDictItemService frameDictItemService;

	@RequestMapping(value = {apiVer ,"/"})
	protected ModelAndView checkCode(
			@RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value = "dictCode", required = false) String dictCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		System.out.println("################");
		Specification<FrameDict> spec=DynamicSpecifications.bySearchFilter(request, FrameDict.class);
		PageRequest pageRequest=DynamicPageRequest.buildPageRequest(1,10,"","",FrameDict.class);
		Page<FrameDict> pag = this.frameDictService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView("index");
	}
}
