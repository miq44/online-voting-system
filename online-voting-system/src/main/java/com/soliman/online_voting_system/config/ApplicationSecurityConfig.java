package com.soliman.online_voting_system.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource secuityDataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	  http.csrf().disable();
		http.
		 csrf()
        .ignoringAntMatchers("/ajaxrequest/**")
        .and()
        .authorizeRequests()
		.antMatchers("/","/dashboard").hasRole("USER")
		.antMatchers("/register","/getCandidateByPost").permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.loginProcessingUrl("/authenticateUser")
		.defaultSuccessUrl("/dashboard")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied");
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(secuityDataSource);
	}
	
	
}
