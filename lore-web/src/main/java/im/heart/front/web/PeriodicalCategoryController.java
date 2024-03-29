package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.service.ServiceException;
import im.heart.core.web.AbstractController;
import im.heart.material.entity.MaterialCategory;
import im.heart.material.service.MaterialCategoryService;
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

@Controller
@RequestMapping("/api")
public class PeriodicalCategoryController extends AbstractController {
	protected static final String apiVer = "/periodical/category";
	@Autowired
	private MaterialCategoryService materialCategoryService;

	/**
	 * 
	 * @功能说明：查询所有
	 * @param request
	 * @param response
	 * @param jsoncallback
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(apiVer+"s")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = RequestResult.ACCESS_TOKEN, required = false) String token,
                             ModelMap model) {
		Specification<MaterialCategory> spec=DynamicSpecifications.bySearchFilter(request, MaterialCategory.class);
		PageRequest pageRequest=DynamicPageRequest.buildPageRequest(page,size,sort,order,MaterialCategory.class);
		Page<MaterialCategory> pag = this.materialCategoryService.findAll(spec, pageRequest);
		super.success(model,pag);
		return new ModelAndView(VIEW_SUCCESS);
	}
	@RequestMapping(value = apiVer+"/{id}",method = RequestMethod.GET)
	protected ModelAndView findByKey(
			@RequestParam(value = RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
			@RequestParam(value =RequestResult.ACCESS_TOKEN , required = false) String token,
			 @PathVariable BigInteger id,
			HttpServletRequest request,
			ModelMap model) {
		MaterialCategory po = this.materialCategoryService.findById(id);
		super.success(model, po);
		return new ModelAndView(VIEW_SUCCESS);
	}
}
