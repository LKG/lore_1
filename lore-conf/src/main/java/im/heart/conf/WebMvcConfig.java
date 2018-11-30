package im.heart.conf;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.google.common.collect.Lists;
import im.heart.core.CommonConst;
import im.heart.core.support.view.JsonpView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: gg
 * @desc : spring mvc 配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${prod.upload.path.root}")
    private String prodUploadFilePath = "";
    @Bean
    public ViewResolver contentNegotiatingViewResolver(
            ContentNegotiationManager manager) {
        // Define the view resolvers
        ViewResolver beanNameViewResolver = new BeanNameViewResolver();
        List<ViewResolver> resolvers = Lists.newArrayList(beanNameViewResolver);
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(resolvers);
        resolver.setContentNegotiationManager(manager);
        return resolver;
    }
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /* favorPathExtension表示支持后缀匹配，是否通过请求Url的扩展名来决定media type */
        configurer.favorPathExtension(true)
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

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.freeMarker();
        registry.enableContentNegotiation(new JsonpView());
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + CommonConst.STATIC_UPLOAD_ROOT + "/**")
                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + prodUploadFilePath);
    }
}
