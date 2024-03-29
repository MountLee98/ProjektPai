package biuro.podrozy.projekt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(passwordEncoder().encode("admin"))
			.roles("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable().headers().frameOptions().sameOrigin().and().authorizeRequests().anyRequest();
		//http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		//http.csrf().disable().headers().frameOptions().sameOrigin().and().authorizeRequests().anyRequest().permitAll();
		http.csrf().disable().headers().frameOptions().sameOrigin().and().authorizeRequests()
			.antMatchers("/dodajWycieczke").authenticated()
				.antMatchers("/addTrip").authenticated()
				.antMatchers("/addCity").authenticated()
				.antMatchers("/addAirport").authenticated()
				.antMatchers("/addCity").authenticated()
				.antMatchers("/addContinent").authenticated()
				.antMatchers("/addHotel").authenticated()
				.antMatchers("/mainAdmin").authenticated()
				.antMatchers("/editTrip/**").authenticated()
				.antMatchers("/deleteTrip/**").authenticated()
				.antMatchers("/swagger-ui.html/**").authenticated()
				.antMatchers("/showTrips").authenticated()
			.anyRequest().permitAll()
			.and().formLogin();
			
	}
	
}
