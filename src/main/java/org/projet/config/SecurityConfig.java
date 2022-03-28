package org.projet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/books","/api/dvds").permitAll()
		.antMatchers("/api/users/**").authenticated()
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/swagger-ui.html")
		.invalidateHttpSession(true);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/**/*.js", "/**/*.css", "/**/*.png");
	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		
//		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER")
//				.and().withUser("admin").password("{noop}password").roles("USER", "ADMIN");
//		}
//	
	
	//On passe cette fonction en commentaire car elle va entrer en conflit avec UserAuthentication.java
	
	
	
	
}
