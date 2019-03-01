package io.hexaforce.webmusic.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(corsFilter(), (Class<? extends Filter>) SessionManagementFilter.class)
		.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
		.and().csrf().ignoringAntMatchers("/**")
		.and().formLogin();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false).inMemoryAuthentication()
		.withUser("user").password("password").roles();
	}

}