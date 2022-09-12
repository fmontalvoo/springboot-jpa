package com.fmontalvoo.springboot.jpa.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fmontalvoo.springboot.jpa.app.services.JpaUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired
//	private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/list").permitAll()
				.antMatchers("/view/**", "/uploads/**").hasAnyRole("USER").antMatchers("/form/**", "/delete/**")
				.hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

//		builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
//				.usersByUsernameQuery("select username, password, enabled from users where username like ?")
//				.authoritiesByUsernameQuery(
//						"select u.username, a.authority from authorities a inner join users u on(a.user_id=u.id) where u.username like ?");

		// PasswordEncoder encoder = passwordEncoder;
//		UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
//		UserBuilder userBuilder = User.builder().passwordEncoder(password -> encoder.encode(password));
//
//		builder.inMemoryAuthentication()
//				.withUser(userBuilder.username("admin").password("Admin.123").roles("ADMIN", "USER"))
//				.withUser(userBuilder.username("frank").password("Admin.123").roles("USER"));
	}

}
