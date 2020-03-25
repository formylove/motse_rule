package ink.moshuier.motse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("dev")
//@EnableWebSecurity
//convenient annotation that enables request authentication through OAuth 2.0 tokens. Normally,
public class SecurityConfigurationDev extends WebSecurityConfigurerAdapter {


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
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET).permitAll().antMatchers(HttpMethod.DELETE).permitAll().antMatchers(HttpMethod.PUT).permitAll().antMatchers(HttpMethod.POST).permitAll();
    }


}