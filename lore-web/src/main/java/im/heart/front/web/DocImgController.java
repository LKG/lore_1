package im.heart.front.web;

import im.heart.core.CommonConst;
import im.heart.core.plugins.persistence.DynamicPageRequest;
import im.heart.core.plugins.persistence.DynamicSpecifications;
import im.heart.core.plugins.persistence.SearchFilter;
import im.heart.core.web.AbstractController;
import im.heart.material.entity.MaterialPeriodicalImg;
import im.heart.material.service.MaterialPeriodicalImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import java.util.Collection;

@Controller
public class DocImgController extends AbstractController {
    protected static final String apiVer = "/doc";
    protected static final String VIEW_LIST="front/doc/doc_list";
    protected static final String VIEW_DETAILS="front/doc/doc_details";
    protected static final String VIEW_CREATE="front/doc/doc_create";
    @Autowired
    private MaterialPeriodicalImgService materialPeriodicalImgService;
    @RequestMapping(value = apiVer+"/{id}/imgs")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value =CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
                             @PathVariable BigInteger id,
                             @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
                             @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
                             @RequestParam(value = "sort", required = false) String sort,
                             @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
                             @RequestParam(value = "access_token", required = false) String token,
                             ModelMap model) {
        final Collection<SearchFilter> filters= DynamicSpecifications.buildSearchFilters(request);
        filters.add(new SearchFilter("periodicalId",SearchFilter.Operator.EQ,id));
        Specification<MaterialPeriodicalImg> spec=DynamicSpecifications.bySearchFilter(filters, MaterialPeriodicalImg.class);
        PageRequest pageRequest=DynamicPageRequest.buildPageRequest(page,size,sort,order,MaterialPeriodicalImg.class);
        Page<MaterialPeriodicalImg> pag = this.materialPeriodicalImgService.findAll(spec, pageRequest);
        super.success(model,pag);
        return new ModelAndView(VIEW_LIST);
    }


}
