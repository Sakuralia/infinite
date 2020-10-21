package io.adana.infinite.admin.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Lenovo
 * @date 2020/7/29 8:48
 * @description
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private UserService userService;

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        return username -> userService.loadUserByUsername(username);
//    }
}
