package com.cy.config.security;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-24 11:33:59
 */

import com.cy.config.security.customerUserDetailsService.CustomerUserDetailService;
import com.cy.config.security.filter.CheckTokenFilter;
import com.cy.config.security.handler.CustomAccessDefineHandler;
import com.cy.config.security.handler.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * spring security配置类
 * @Configuration： 表明该类是一个配置类
 * @EnableWebSecurity: 启用spring security
 * @EnableGlobalMethodSecurity: 启用spring security注解
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private CustomAccessDefineHandler customAccessDefineHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private CheckTokenFilter checkTokenFilter;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //解决跨域
        http.cors().and().headers().frameOptions().disable();
        http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //关闭跨域请求伪
        http.csrf().disable()
                //基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //放行登录请求,其他的所有请求都要认证
                .antMatchers("/api/user/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDefineHandler)
                .authenticationEntryPoint(loginFailureHandler);
    }

    //注入AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailService);
    }
}
