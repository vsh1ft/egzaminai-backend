package lt.codedicted.egzaminai.backend.config

import lt.codedicted.egzaminai.backend.JwtSecretProvider
import lt.codedicted.egzaminai.backend.jwt.JWTAuthenticationFilter
import lt.codedicted.egzaminai.backend.jwt.JWTLoginFilter
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
class SecurityConfig(
    private val authFilter: JWTAuthenticationFilter,
    private val jwtSecretProvider: JwtSecretProvider
): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .httpBasic().disable()
            .authorizeRequests()
            .antMatchers("/test/").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(
                jwtLoginFilterBean(),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = authenticationManager()

    @Bean
    fun jwtLoginFilterBean() = JWTLoginFilter(jwtSecretProvider).apply {
        setAuthenticationManager(authenticationManagerBean())
    }

}
