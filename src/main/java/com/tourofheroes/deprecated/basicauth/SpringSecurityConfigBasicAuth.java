package com.tourofheroes.deprecated.basicauth;

//import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class SpringSecurityConfigBasicAuth extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable() // Disable CSRF because we use it in JWT, not in form-based auth
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Permit all calls from OPTIONS method
		.anyRequest().authenticated().and().httpBasic(); // Permit only requests that has authentication
	}
}
