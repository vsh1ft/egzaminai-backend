package lt.codedicted.egzaminai.backend.config.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import lt.codedicted.egzaminai.backend.config.JwtSecretProvider
import lt.codedicted.egzaminai.backend.model.JwtToken
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.model.SecurityUserDetails
import lt.codedicted.egzaminai.backend.repository.TokenRepository
import lt.codedicted.egzaminai.backend.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTCreateFilter(
    private val userService: UserService,
    private val jwtSecretProvider: JwtSecretProvider,
    private val tokenRepository: TokenRepository
): AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher(
        "/user/create",
        HttpMethod.POST.toString()
    )
) {

    @Autowired
    override fun setAuthenticationManager(authenticationManager: AuthenticationManager) {
        super.setAuthenticationManager(authenticationManager)
    }

    override fun attemptAuthentication(req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val user: User = jacksonObjectMapper().readValue(req.inputStream)
        userService.create(user)

        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                user.email,
                user.password,
                emptyList<GrantedAuthority>()
            )
        )
    }

    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse, chain: FilterChain?, auth: Authentication
    ) {
        val token = ObjectMapper().writeValueAsString((auth.principal as SecurityUserDetails).user.createJwt())
        res.setHeader("Access-Control-Allow-Origin", "*")
        res.writer.write(token)
        res.writer.flush()
        res.writer.close()
        tokenRepository.save(JwtToken(token.replace("\"", "")))
    }

    private fun User.createJwt(): String {
        return Jwts.builder()
            .setClaims(hashMapOf<String, Any>("roles" to this.roles))
            .setSubject(this.email)
            .setExpiration(Date(Date().time + TimeUnit.HOURS.toMillis(8)))
            .signWith(Keys.hmacShaKeyFor(jwtSecretProvider.value.toByteArray()), SignatureAlgorithm.HS512)
            .compact()
    }
}
