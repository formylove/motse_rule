package ink.moshuier.motse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
//@EnableWebSecurity
//convenient annotation that enables request authentication through OAuth 2.0 tokens. Normally,
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final String[] STATIC_RESOURCES = {
            "/",
            "/static/**",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/api/v1/file/download/**",
    };
    //你要登陆论坛，输入用户名张三，密码1234，密码正确，证明你张三确实是张三，这就是 authentication；再一check用户张三是个版主，所以有权限加精删别人帖，这就是 authorization。


    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.oauth2Login().and()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and().antMatcher("/**").authorizeRequests()
//                .antMatchers("/", "/current").permitAll()
//                .anyRequest().authenticated()
//                // set logout URL
//                .and().logout().logoutSuccessUrl("/")
//                .and().oauth2Login();
//        OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer jwt = http.oauth2ResourceServer().jwt();
        http.oauth2ResourceServer().jwt();
    }


    //    Spring Security Web认证机制(通常指表单登录)中登录成功后页面需要跳转到原来客户请求的URL。该过程中首先需要将原来的客户请求缓存下来，然后登录成功后将缓存的请求从缓存中提取出来。
//    针对该需求，Spring Security Web 提供了在http session中缓存请求的能力，也就是HttpSessionRequestCache。HttpSessionRequestCache所保存的请求必须封装成一个SavedRequest接口对象，实际上，HttpSessionRequestCache总是使用自己的SavedRequest缺省实现DefaultSavedRequest。
//    The RequestCache bean overrides the default request cache. It saves the referrer header (misspelled referer in real life), so Spring Security can redirect back to it after authentication. The referrer-based request cache comes in handy when you’re developing React on http://localhost:3000 and want to be redirected back there after logging in.
    @Bean
    public RequestCache refererRequestCache() {
        return new HttpSessionRequestCache() {
            @Override
            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                String referrer = request.getHeader("referer");
                if (referrer != null) {
                    request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
                }
            }
        };
    }
}