package com.pjh.restfulwebservice.user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration spring boot 기동하면서 memory에 할당
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //H2 library를 사용하기위한 security 예외처리
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //h2-console~ permition 통과
        http.authorizeRequests().antMatchers("/h2-onsole/**").permitAll();
        //cross side script 사용안함
        http.csrf().disable();
        //frame option 사용안함
        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication()
                .withUser("pjh")
                .password("{noop}test1234")
                .roles("N_USER");
    }
}
