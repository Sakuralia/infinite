package io.adana.infinite.admin.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private IUserDetailApi iUserDetailApi;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/index.html").loginProcessingUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/login", "/resources").permitAll()
                .anyRequest()       //access every requests.
                .authenticated()    //need to authenticate identity
                .and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutSuccessHandler(iLogoutSuccessHandler)
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * the function of the provider which authenticate user according the context of name and password.
         */
        auth.authenticationProvider(null);
        /**
         * the customer's provider is used for signing in.
         */
        auth.authenticationProvider(null);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
