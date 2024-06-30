package ru.netology.hibernate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("Fyodor")
                .password("111")
                .roles("READ", "WRITE", "DELETE")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("Ivan")
                .password("222")
                .roles("READ", "WRITE")
                .build();
        UserDetails user3 = User.withDefaultPasswordEncoder()
                .username("Pavel")
                .password("333")
                .roles("READ")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}
