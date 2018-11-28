package im.heart.conf;

import im.heart.core.CommonConst;
import im.heart.core.support.view.JsonpView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author: gg
 * @desc : spring mvc 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${prod.upload.path.root}")
    private String prodUploadFilePath = "";
    @Value("${info.app-version}")
    private String appVersion = "";
    @Bean
    public JsonpView jsonpView() {
        return new JsonpView();
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /* 是否通过请求Url的扩展名来决定media type */
        configurer.favorPathExtension(true)
                /* 不检查Accept请求头 */
                .ignoreAcceptHeader(true)
                /* 设置默认的MediaType */
                .parameterName("format")
                .defaultContentType(MediaType.TEXT_HTML)
                 /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
                .mediaType("jhtml", MediaType.TEXT_HTML)
                .mediaType("html", MediaType.TEXT_HTML);
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.viewResolver(jsonpView());
        registry.enableContentNegotiation(jsonpView());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + CommonConst.STATIC_UPLOAD_ROOT + "/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + prodUploadFilePath);
    }
}
