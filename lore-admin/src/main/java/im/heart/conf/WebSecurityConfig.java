package im.heart.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 *
 * @author gg
 * @desc : 认证配置
 * //启用web权限
 * //启用方法验证
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                //任何访问都必须授权
//                .anyRequest().fullyAuthenticated()
//                //配置那些路径可以不用权限访问
//                .mvcMatchers("/login", "/login/wechat").permitAll()
//                .and()
//                .formLogin()
//                //登陆成功后的处理，因为是API的形式所以不用跳转页面
//                .successHandler(new MyAuthenticationSuccessHandler())
//                //登陆失败后的处理
//                .failureHandler(new MySimpleUrlAuthenticationFailureHandler())
//                .and()
//                //登出后的处理
//                .logout().logoutSuccessHandler(new RestLogoutSuccessHandler())
//                .and()
//                //认证不通过后的处理
//                .exceptionHandling()
//                .authenticationEntryPoint(new RestAuthenticationEntryPoint());
//        http.addFilterAt(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
//        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().disable();
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html**",
                        "/webjars/**");
    }
}
