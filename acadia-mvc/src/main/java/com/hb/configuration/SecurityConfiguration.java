package com.hb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustombCryptPasswordEncoder custombCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	@Value("${application.profile}")
	private String profile;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(custombCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (profile.equals("local")) {
			http.
			authorizeRequests().antMatchers("/**").permitAll().anyRequest()
//				.antMatchers("/trainings/**").permitAll()
//				.antMatchers("/training/**").permitAll()
//				.antMatchers("/categories").permitAll()
//				.antMatchers("/login").permitAll()
//				.antMatchers("/register").permitAll()
//				.antMatchers("/admin/**").hasAuthority("ROLE_CUSTOMER") // FOR DEVs, DO NOT KEEP IT ENABLE !!!Q
//				.antMatchers("/app/**").hasAuthority("ROLE_CUSTOMER")
//				.antMatchers("/trainer/**").hasAuthority("ROLE_TRAINER").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/dashboard", true)
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
		} else {
			http.
			authorizeRequests()
				.antMatchers("/trainings/**").permitAll()
				.antMatchers("/training/**").permitAll()
				.antMatchers("/categories").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/app/**").hasAuthority("ROLE_CUSTOMER")
				.antMatchers("/trainer/**").hasAuthority("ROLE_TRAINER").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/dashboard", true)
				.usernameParameter("email")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
		}
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers( "/static/**", "/css/**", "/fonts/**", "/images/**, /javascript/**");
	}
}