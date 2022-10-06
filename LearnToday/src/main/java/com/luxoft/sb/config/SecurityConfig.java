/*
 * package com.luxoft.sb.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration; import
 * org.springframework.security.config.annotation.method.configuration.
 * EnableGlobalMethodSecurity; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * import com.luxoft.sb.security.CustomUserDetailsService;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @EnableGlobalMethodSecurity(prePostEnabled = true) public class
 * SecurityConfig { // @Autowired // private CustomUserDetailsService
 * userDetailsService;
 * 
 * @Bean protected SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * 
 * http.csrf().disable() .authorizeRequests()
 * .antMatchers("/api/Student/**").permitAll()
 * .antMatchers("/api/Admin/**").permitAll()
 * .antMatchers("/api/Trainer/**").permitAll() .anyRequest() .authenticated()
 * .and() .httpBasic(); return http.build(); }
 * 
 * @Bean protected AuthenticationManager
 * authenticationManager(AuthenticationConfiguration
 * authenticationConfiguration) throws Exception { return
 * authenticationConfiguration.getAuthenticationManager(); } }
 */