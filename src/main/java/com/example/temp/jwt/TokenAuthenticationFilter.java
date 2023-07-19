package com.example.temp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    private static final String SECRET_KEY="my_secret_key";

    private static final String BEARER_PREFIX = "Bearer ";
    private static final int SUBSTRING_BEARER_INDEX = 7;
    private static final String AUTHORIZATION_HEADER = "authorization";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return resolveToken(request);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return resolveToken(request);
    }

    public String resolveToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHORIZATION_HEADER);

        if (validateToken(jwtToken)) {
            return parseBearerToken(jwtToken);
        }
        return null;
    }

    private boolean validateToken(String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith(BEARER_PREFIX)) {
            return false;
        }

        Jws<Claims> claims =
                Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .build()
                        .parseClaimsJws(parseBearerToken(jwtToken));

        return !claims.getBody().getExpiration().before(new Date());
    }

    private String parseBearerToken(String jwtToken) {
        return jwtToken.substring(SUBSTRING_BEARER_INDEX);
    }
}
