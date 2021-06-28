package com.epam.library.configuration;

import com.epam.library.model.Permission;
import com.epam.library.model.Role;
import com.epam.library.security.jwt.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtConfigurer jwtConfigurer;

	public SecurityConfig(JwtConfigurer jwtConfigurer) {
		this.jwtConfigurer = jwtConfigurer;
	}

	@Override
	/**
	 * Разрешение GET эндпоинтов для ролей, имеющих READ пермишн, POST - WRITE пермишен, любой реквест требует аутентификации, Basic Auth тип
	 */
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/auth/login").permitAll()
				.antMatchers("/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/**").hasAuthority(Permission.READ.getPermission())
				.antMatchers(HttpMethod.POST, "/**").hasAuthority(Permission.WRITE.getPermission())
				.anyRequest()
				.authenticated()
				.and()
				.apply(jwtConfigurer);
				//.and()
				//.formLogin(); Тоже самое что и httpBasic, но в виде стандартной спринг секьюрити формы, автоматически замапленная на /login
				//.loginPage("auth/login").permitAll(); кастомный formLogin(), необходимо создать эндпоинт с перенаправлением на вью
				//.defaultSuccessfulUrl("auth/success").permitAll(); кастомная success страница, необходимо создать эндпоинт с перенаправлением на вью
				//.httpBasic(); //Basic Auth
//				.and()
//				.logout() //настройка логаута
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST")) // установка логаута только при пост запросе
//				.invalidateHttpSession(true) // инвалидируем сессию
//				.clearAuthentication(true) // убираем хедер аутентификейшн
//				.deleteCookies("JSESSIONID") // удаляем JSESSION из куки
//				.logoutSuccessUrl("/login"); //перенаправляемся на логин

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	/**
	 * Создание in-memory пользователей в приложении (лог-пасс-роль-права)
	 */
	protected UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.builder().username("user").password(passwordEncoder().encode("user")).authorities(Role.USER.getAuthorities()).build(),
				User.builder().username("admin").password(passwordEncoder().encode("admin")).authorities(Role.ADMIN.getAuthorities()).build()
		);
	}

	@Bean
	protected  PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
