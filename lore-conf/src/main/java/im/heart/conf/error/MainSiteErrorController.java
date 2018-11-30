//package im.heart.conf.error;
//import im.heart.core.CommonConst.RequestResult;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author gg
// * @desc 自定义错误页面
// */
//@Controller
//public class MainSiteErrorController implements ErrorController {
//	private static final String ERROR_PATH = "/error";
//	protected Map<String, Object> error(HttpServletRequest request, ModelMap model,HttpStatus httpStatus) {
//		model.put(RequestResult.HTTP_STATUS,httpStatus.value());
//		model.put(RequestResult.SUCCESS, false);
//		model.put("request", request.getRequestURL());
//		return model;
//	}
//
//	@RequestMapping(value = ERROR_PATH)
//	public ModelAndView handleError(HttpServletRequest request, ModelMap model){
//		this.error(request,model,HttpStatus.INTERNAL_SERVER_ERROR);
//		return new ModelAndView(RequestResult.PAGE_ERROR);
//	}
//	@RequestMapping(value = ERROR_PATH+"/404")
//	public ModelAndView handle404Error(HttpServletRequest request, ModelMap model){
//		this.error(request,model,HttpStatus.NOT_FOUND);
//		return new ModelAndView("errors/404");
//	}
//	@RequestMapping(value = ERROR_PATH+"/405")
//	public ModelAndView handle405Error(HttpServletRequest request,ModelMap model){
//		this.error(request,model,HttpStatus.METHOD_NOT_ALLOWED);
//		return new ModelAndView("errors/405");
//	}
//
//	@RequestMapping(value = ERROR_PATH+"/500")
//	public ModelAndView handle500Error(HttpServletRequest request,ModelMap model){
//		this.error(request,model,HttpStatus.INTERNAL_SERVER_ERROR);
//		return new ModelAndView("errors/500");
//	}
//	@Override
//	public String getErrorPath() {
//		return ERROR_PATH;
//	}
//
//}
