package io.adana.infinite.admin.config;

import io.adana.infinite.admin.handler.ILogoutSuccessHandler;
import io.adana.infinite.auth.service.UserAuthenticationProvider;
import io.adana.infinite.user.api.IUserDetailApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author Lenovo
 * @date 2020/7/29 8:48
 * @description
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ILogoutSuccessHandler iLogoutSuccessHandler;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private IUserDetailApi iUserDetailApi;

    /**
     * configure the permission controller of urls.
     *
     * @param http Spring Security's XML &lt;http&gt; element in the namespace configuration
     * @throws Exception some exceptions
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 登录页面
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(null)
                .failureHandler(null)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/index.html",
                        "/login",
                        "/resources",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**"
                ).permitAll()
                // send every requests with the method type of options
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()       //access every requests.
                .authenticated()    //need to authenticate identity
                .and()
                .logout()
                // logout
                .logoutSuccessHandler(iLogoutSuccessHandler)
                .and()
                // configure the expired time that is one hour.
                .rememberMe()
                .tokenValiditySeconds(3600)
                .and()
                // close csrf
                .csrf().disable()
                // 基于token
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and();
        //disabled cache.
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * the function of the provider which authenticate user according the context of name and password.
         */
        auth.authenticationProvider(new UserAuthenticationProvider(iUserDetailApi));
        /**
         * the customer's provider is used for signing in.
         */
        // todo 编写对应的提供类
        auth.authenticationProvider(null);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.GET,
                "/swagger**/**",
                "/admin/**",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/**",
                "/doc.html");
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Encrypt the password
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
