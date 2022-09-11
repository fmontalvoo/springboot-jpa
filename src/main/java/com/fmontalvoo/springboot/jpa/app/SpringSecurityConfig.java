package com.fmontalvoo.springboot.jpa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/list").permitAll()
				.antMatchers("/view/**", "/uploads/**").hasAnyRole("USER")
				.antMatchers("/form/**", "/delete/**", "/factura/**").hasAnyRole("ADMIN").anyRequest().authenticated();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder encoder = this.passwordEncoder();
		UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//		UserBuilder userBuilder = User.builder().passwordEncoder(password -> encoder.encode(password));

		builder.inMemoryAuthentication()
				.withUser(userBuilder.username("admin").password("Admin.123").roles("ADMIN", "USER"))
				.withUser(userBuilder.username("frank").password("Admin.123").roles("USER"));
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
