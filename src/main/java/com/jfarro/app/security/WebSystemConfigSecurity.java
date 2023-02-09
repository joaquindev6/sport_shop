package com.jfarro.app.security;

import com.jfarro.app.security.handlers.ErrorAccessDeniedHandler;
import com.jfarro.app.security.handlers.LoginSuccessHandler;
import com.jfarro.app.services.impl.UserJpaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSystemConfigSecurity {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private ErrorAccessDeniedHandler errorAccessDeniedHandler;

    @Autowired
    private UserJpaDetailsService userJpaDetailsService;

    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withUsername("joaquin")
                .password(passwordEncoder.encode("12345"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.withUsername("jose")
                .password(passwordEncoder.encode("12345"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userJpaDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/", "/inicio/**", "/deporte/**", "/photoproducts/**", "/system-sport-shop/photoproducts/**",
                        "/carrito", "/carrito/additem/**", "/carrito/remove/**", "/carrito/cargar-productos/**",
                        "/sistema/**", "/tienda/**").permitAll()
                .requestMatchers("/carrito/save_sale/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/system-sport-shop/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().successHandler(loginSuccessHandler).loginPage("/login").permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedHandler(errorAccessDeniedHandler);

        return http.build();
    }
}
