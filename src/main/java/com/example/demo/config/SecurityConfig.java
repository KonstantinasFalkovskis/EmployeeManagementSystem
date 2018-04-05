/**
 * @Author - Falco Constantine
 * @date - 2018.04.02
 * @version - v.1.0
 */
package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring security configuration
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email as principal, password as credentails, true from user where email=?")
                .authoritiesByUsernameQuery("select user_email as principal, role_name as role from user_roles where user_email=?")
                .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/", "/login", "/css/**", "/webjars/**").permitAll()
                .antMatchers("/profile", "/employees", "/employee/view/{id}", "/about").hasAnyRole("USER,ADMIN")
                .antMatchers("/register", "/users", "/addTask", "/employees/add").hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll()
				//.defaultSuccessUrl("/profile").and().logout().logoutSuccessUrl("/login");
                .defaultSuccessUrl("/profile").and().logout().logoutSuccessUrl("/");

    }


}
