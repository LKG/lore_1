package im.heart.conf;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import im.heart.core.CommonConst;
import im.heart.core.support.view.JsonpView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.List;

/**
 *
 * @author: gg
 * spring mvc 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${prod.upload.path.root}")
    private String prodUploadFilePath = "";
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //setUseSuffixPatternMatch 后缀模式匹配
        configurer.setUseSuffixPatternMatch(true);
        //setUseTrailingSlashMatch 自动后缀路径模式匹配
        configurer.setUseTrailingSlashMatch(true);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /* favorPathExtension表示支持后缀匹配，是否通过请求Url的扩展名来决定media type */
        configurer.favorPathExtension(false)
                .useRegisteredExtensionsOnly(false)
                /* 不检查Accept请求头 */
                .ignoreAcceptHeader(true)
                /* 设置默认的MediaType */
                .parameterName("format")
                 /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("jhtml", MediaType.TEXT_HTML)
                .mediaType("html", MediaType.TEXT_HTML)
                .mediaType("xml", MediaType.APPLICATION_ATOM_XML)
                .mediaType("pdf", MediaType.APPLICATION_PDF);
    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.enableContentNegotiation(new JsonpView());
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + CommonConst.STATIC_UPLOAD_ROOT + "/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + prodUploadFilePath);
    }
}
