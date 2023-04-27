package com.example.sqlexecutorservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy


@Configuration
@EnableWebSecurity
internal class SecurityConfig(keycloakLogoutHandler: KeycloakLogoutHandler) {
    private val keycloakLogoutHandler: KeycloakLogoutHandler

    init {
        this.keycloakLogoutHandler = keycloakLogoutHandler
    }

    @Bean
    protected fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return RegisterSessionAuthenticationStrategy(SessionRegistryImpl())
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/sql-executor/*")
            .fullyAuthenticated()
            .and()
            .oauth2Login()
            .and()
            .oauth2ResourceServer().jwt()
        return http.build()
    }


}