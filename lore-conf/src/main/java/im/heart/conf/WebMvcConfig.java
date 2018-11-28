//package im.heart.conf;
//
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import com.google.common.collect.Lists;
//import im.heart.core.CommonConst;
//import im.heart.core.support.view.JsonpView;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.ByteArrayHttpMessageConverter;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.util.ResourceUtils;
//import org.springframework.web.accept.ContentNegotiationManager;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.*;
//import org.springframework.web.servlet.view.BeanNameViewResolver;
//import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author: gg
// * @desc : spring mvc 配置
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Value("${prod.upload.path.root}")
//    private String prodUploadFilePath = "";
//    @Bean
//    public JsonpView jsonpView() {
//        return new JsonpView();
//    }
//    @Bean(name = "fastJsonHttpMessageConverter")
//    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//        supportedMediaTypes.add(MediaType.TEXT_HTML);
//        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        return fastJsonHttpMessageConverter;
//    }
//    @Bean(name = "stringHttpMessageConverter")
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
//        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
//        supportedMediaTypes.add(MediaType.TEXT_HTML);
//        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//        stringHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
//        return stringHttpMessageConverter;
//    }
//
//    @Bean
//    public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
//        return new ByteArrayHttpMessageConverter();
//    }
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(byteArrayHttpMessageConverter());
//        converters.add(stringHttpMessageConverter());
//        converters.add(fastJsonHttpMessageConverter());
//    }
//    @Bean
//    public ViewResolver contentNegotiatingViewResolver(
//            ContentNegotiationManager manager) {
//        ViewResolver beanNameViewResolver = new BeanNameViewResolver();
//        List<ViewResolver> resolvers = Lists.newArrayList(beanNameViewResolver);
//        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
//        resolver.setViewResolvers(resolvers);
//        resolver.setContentNegotiationManager(manager);
//        return resolver;
//    }
//    /**
//     * 静态资源处理类
//     * @param configurer
//     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        /* favorPathExtension表示支持后缀匹配，是否通过请求Url的扩展名来决定media type */
//        configurer.favorPathExtension(false)
//                /* 不检查Accept请求头 */
//                .ignoreAcceptHeader(true)
//                /* 设置默认的MediaType */
//                .parameterName("format")
//                .defaultContentType(MediaType.TEXT_HTML)
//                 /* 请求以.html结尾的会被当成MediaType.TEXT_HTML*/
//                .mediaType("jhtml", MediaType.TEXT_HTML)
//                .mediaType("json", MediaType.APPLICATION_JSON_UTF8)
//                .mediaType("html", MediaType.TEXT_HTML);
//    }
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.enableContentNegotiation(jsonpView());
//    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/" + CommonConst.STATIC_UPLOAD_ROOT + "/**")
//                .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + prodUploadFilePath);
//    }
//}
