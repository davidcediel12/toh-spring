package com.tourofheroes.tourofheroes.securtiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tourofheroes.tourofheroes.securtiry.filter.JwtFilterRequest;
import com.tourofheroes.tourofheroes.services.AuthUsersService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private AuthUsersService authService;

	@Autowired
	private JwtFilterRequest jwtFilter; // Para pasar las peticiones por este filtro antes

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Le decimos que nuestro servicio es el que va a manejar la configuracion
		auth.userDetailsService(authService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Le decimos cuales son los criterios para aceptar requests
		http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate", "/**/newUser").permitAll()
				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors(); // Super importante, sin esto angular se tuesta
	}

	// This override is only for add the @Bean decorator
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(authService);
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
