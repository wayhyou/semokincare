package com.semokincare.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.semokincare.service.UserDetailService;
import com.semokincare.service.UserService;
import com.semokincare.utils.exceptions.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Get and Validate request client
            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

            String clientToken = null;
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                clientToken = headerAuth.substring(7);
            }

            if (clientToken != null && jwtUtil.verifyToken(clientToken)) {
                Map<String, String> userInfo = jwtUtil.getUserInfoByToken(clientToken);
                UserDetails user = userDetailService.loadUserById(userInfo.get("userId"));

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                authenticationToken.setDetails(new WebAuthenticationDetailsSource());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                request.setAttribute("userId", userInfo.get("userId"));
            }
        } catch (JWTVerificationException e) {
            throw new AuthenticationException("Auth Error: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
