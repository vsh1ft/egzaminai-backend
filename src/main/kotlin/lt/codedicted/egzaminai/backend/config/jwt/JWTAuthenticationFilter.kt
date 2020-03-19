package lt.codedicted.egzaminai.backend.config.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import lt.codedicted.egzaminai.backend.config.JwtSecretProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class JWTAuthenticationFilter(private val jwtSecretProvider: JwtSecretProvider): GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val authentication = getAuthentication(request as HttpServletRequest)
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader("Authorization") ?: return null

        val tokenBody = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(jwtSecretProvider.value.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body

        val username: String = tokenBody.subject
        val roles = tokenBody["roles"] as List<String>

        val res = roles.mapTo(LinkedList<GrantedAuthority>()) { SimpleGrantedAuthority(it) }

        return UsernamePasswordAuthenticationToken(username, null, res)
    }
}
