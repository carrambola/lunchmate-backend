package com.example.lunchmateback.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.lunchmateback.jwt.AuthEntryPointJwt;
import com.example.lunchmateback.jwt.AuthTokenFilter;
import com.example.lunchmateback.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // @Bean
    // UserDetailsService userDetailsService() {
    //     return new UserDetailsServiceImpl();
    // }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        // authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.antMatchers("/api/auth/**").permitAll()
                        .antMatchers("/api/test/**").permitAll()
                        .antMatchers("/api/recipes/**").permitAll()
                        .antMatchers("/api/comments/**").permitAll()
                        .antMatchers("/api/categories/**").permitAll()
                        .antMatchers("/api/ingridients/**").permitAll()
                        .antMatchers("/api/user/**").permitAll()
                        .anyRequest().authenticated());

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    //  @Bean
    // public CorsFilter corsFilter() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.addAllowedOrigin("*"); // Ustaw dozwolone domeny
    //     config.addAllowedMethod("*"); // Ustaw dozwolone metody HTTP
    //     config.addAllowedHeader("*"); // Ustaw dozwolone nagłówki
    //     source.registerCorsConfiguration("/**", config);
    //     return new CorsFilter(source);
    // }
    

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {

    // http.authorizeHttpRequests((a) -> a.anyRequest().authenticated())
    // .httpBasic(Customizer.withDefaults())
    // .formLogin(Customizer.withDefaults());

    // return http.build();
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // // TODO: Na razie wersja bez bazy danych
    // UserDetails userDetails = User
    // .withDefaultPasswordEncoder()
    // .username("tester")
    // .password("123456")
    // .roles("USER").build();

    // return new InMemoryUserDetailsManager(userDetails);
    // }
}
