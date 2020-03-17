package lt.codedicted.egzaminai.backend.jwt

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import lt.codedicted.egzaminai.backend.JwtSecretProvider
import lt.codedicted.egzaminai.backend.model.User
import lt.codedicted.egzaminai.backend.service.SecurityUserDetails
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter(private val jwtSecretProvider: JwtSecretProvider) : AbstractAuthenticationProcessingFilter(
    AntPathRequestMatcher(
        "/login",
        HttpMethod.POST.toString()
    )
) {

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(
            req: HttpServletRequest, res: HttpServletResponse): Authentication {
        val cred: AccountCredentials? = jacksonObjectMapper().readValue(req.inputStream)

        return authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        cred?.username,
                        cred?.password,
                        emptyList<GrantedAuthority>()
                )
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(req: HttpServletRequest,
                                          res: HttpServletResponse, chain: FilterChain?, auth: Authentication) {
        addAuthentication(res, (auth.principal as SecurityUserDetails).user)
    }

    private fun addAuthentication(response: HttpServletResponse, user: User) {
        response.writer.write(user.createJwt())
        response.writer.flush()
        response.writer.close()
    }

    private fun User.createJwt(): String {
        println("secret: ${jwtSecretProvider.value}")
        return Jwts.builder()
            .setClaims(hashMapOf<String, Any>("roles" to this.roles))
            .setSubject(this.username)
            .setExpiration(Date(Date().time + TimeUnit.HOURS.toMillis(8)))
            .signWith(Keys.hmacShaKeyFor(jwtSecretProvider.value.toByteArray()), SignatureAlgorithm.HS512)
            .compact()
    }
}
