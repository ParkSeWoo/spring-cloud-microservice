package com.doldol.study.auth.security.config;

import com.doldol.study.core.property.JwtConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {
	private final UserDetailsService userDetailsService;
	private final JwtConfiguration jwtConfiguration;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable ()
				.cors().configurationSource (request -> new CorsConfiguration().applyPermitDefaultValues())
				.and()
					.sessionManagement ().sessionCreationPolicy (STATELESS)
				.and()
					.addFilter(new UsernamePasswordAuthenticationFilter())
				.authorizeRequests()
					.antMatchers (jwtConfiguration.getLoginUrl()).permitAll()
					.antMatchers ("/course/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder ();
	}

}
