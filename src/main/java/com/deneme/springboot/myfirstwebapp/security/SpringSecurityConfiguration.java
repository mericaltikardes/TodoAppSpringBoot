package com.deneme.springboot.myfirstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.function.Function;

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

}
