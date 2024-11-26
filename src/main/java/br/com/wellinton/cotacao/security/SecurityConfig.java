package br.com.wellinton.cotacao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private SecurityFilter securityFilter;
    
     @Autowired
    private HandlerMappingIntrospector handlerMappingIntrospector;
    
         @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(authorize ->
            authorize
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                    .requestMatchers(new MvcRequestMatcher(handlerMappingIntrospector, "/api/indicador/**")).hasRole("USER")
                    .requestMatchers(new MvcRequestMatcher(handlerMappingIntrospector, "/api/cotacao/**")).hasRole("USER")
                    .requestMatchers("/api/**").hasRole("ADMIN")
                    .requestMatchers("/faces/**") 
                    .permitAll()
                .anyRequest().authenticated() 
                   
        )
                        
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                        
	        return http.build();
	    }	
         
         @Bean
         public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
             return authenticationConfiguration.getAuthenticationManager();
         }
         
         @Bean
         public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	 }
            
}