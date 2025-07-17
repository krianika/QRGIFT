package com.qr.investment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
            .antMatchers("/", "/api/users/register", "/api/contributions/**", "/contributions/**", "/qr/**").permitAll()
            .antMatchers("/admin/**", "/api/investments/**", "/invest/**").hasRole("ADMIN")
            .anyRequest().authenticated()
          .and()
            .httpBasic()
          .and()
            .formLogin()
              .loginPage("/login")
              .defaultSuccessUrl("/admin/users", true)
              .permitAll()
          .and()
            .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("{noop}password").roles("ADMIN");
    }
}
