package im.heart.front.web;


import im.heart.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Controller
public class HomeController extends AbstractController {
	protected static final String apiVer = "/index";
	protected static final String VIEW="index";

	@RequestMapping(value={"/welcome",apiVer+"/welcome"},method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		super.success(model);
		return new ModelAndView("front/welcome");
	}
	/**
	 * @功能说明： 关于我们
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/aboutus",apiVer+"/aboutus"} ,method = RequestMethod.GET)
	public ModelAndView aboutus(HttpServletRequest request) {
		return new ModelAndView("front/aboutus");
	}

	/**
	 * @功能说明： VIP
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/vip",apiVer+"/vip"} ,method = RequestMethod.GET)
	public ModelAndView vip(HttpServletRequest request) {
		return new ModelAndView("front/vip");
	}
	/**
	 * @功能说明： 新手帮助
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/helps",apiVer+"/helps"} ,method = RequestMethod.GET)
	public ModelAndView helps(HttpServletRequest request) {
		return new ModelAndView("front/helps");
	}
	
	/**
	 * @功能说明： 联系我们
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/contact",apiVer+"/contact"}  ,method = RequestMethod.GET)
	public ModelAndView contact(HttpServletRequest request) {
		return new ModelAndView("front/contact");
	}


	/**
	 * @功能说明： 常见问题
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/questions",apiVer+"/questions"}  ,method = RequestMethod.GET)
	public ModelAndView questions(HttpServletRequest request) {
		return new ModelAndView("front/questions");
	}


	/**
	 * @功能说明： 协议
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/agreements",apiVer+"/agreements"}  ,method = RequestMethod.GET)
	public ModelAndView agreements(HttpServletRequest request) {
		return new ModelAndView("front/agreements");
	}

}
