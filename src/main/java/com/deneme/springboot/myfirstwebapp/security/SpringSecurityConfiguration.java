package com.deneme.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {


//    InMemoryUserDetailsManager
//    InMemoryUserDetailsManager(UserDetails... users)
//    user.withDefaultPasswordEncoder is deprecated
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {


        String username = "meric";
        String password = "1234";

        UserDetails userDetails = createNewUser(username, password);
        UserDetails userDetails1 = createNewUser("melih", "1234");
        return new InMemoryUserDetailsManager(userDetails,userDetails1);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String,String> passwordEncoder
                =input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //h2 db sine bağlantı sağlayabilmek için spring securityden istisna yaratmak için !!
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //tüm http isteklerinin kimlik doğrulaması gerektirdiğini belirtiyor.
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        //Form kimlik doğarulamasını default yapar
        http.formLogin(withDefaults());
        //CSRF (Cross-Site Request Forgery)  doğrulamasını devre dışı bırakır
        http.csrf().disable();
        //xss saldıları için bir önlem
        http.headers().frameOptions().disable();
        return http.build();
    }
}
