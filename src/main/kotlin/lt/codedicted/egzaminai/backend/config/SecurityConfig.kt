package lt.codedicted.egzaminai.backend.config

import lt.codedicted.egzaminai.backend.config.jwt.JWTAuthenticationFilter
import lt.codedicted.egzaminai.backend.config.jwt.JWTCreateFilter
import lt.codedicted.egzaminai.backend.config.jwt.JWTLoginFilter
import lt.codedicted.egzaminai.backend.repository.TokenRepository
import lt.codedicted.egzaminai.backend.service.UserService
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.session.SessionManagementFilter


@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
class SecurityConfig(
    private val authFilter: JWTAuthenticationFilter,
    private val jwtSecretProvider: JwtSecretProvider,
    private val userService: UserService,
    private val repo: TokenRepository

): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(*getAllowedMatchers()).permitAll()
            .antMatchers(HttpMethod.GET, *getRequests()).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(CorsFilter(), SessionManagementFilter::class.java)
            .addFilterBefore(
                createLoginFilterBean(),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                createRegistrationFilterBean(),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = authenticationManager()

    @Bean
    fun createLoginFilterBean() = JWTLoginFilter(jwtSecretProvider, repo)
        .apply {
            setAuthenticationManager(authenticationManagerBean())
        }

    @Bean
    fun createRegistrationFilterBean() = JWTCreateFilter(userService, jwtSecretProvider, repo)
        .apply {
            setAuthenticationManager(authenticationManagerBean())
        }

    private fun getRequests() = arrayOf(
        "/exams",
        "/programs",
        "/credits",
        "/dates",
        "/pupp-dates",
        "/pupp-exams",
        "/pupp-programs",
        "/database-version"
    )

    private fun getAllowedMatchers() = arrayOf(
        "/user/login",
        "/user/exist/{email}",
        "/user/create",
        "/user/change-password",
        "/user/reset-password"
    )

}
