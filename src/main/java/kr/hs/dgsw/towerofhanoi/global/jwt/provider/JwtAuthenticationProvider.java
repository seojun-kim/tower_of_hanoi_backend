package kr.hs.dgsw.towerofhanoi.global.jwt.provider;

import io.jsonwebtoken.Claims;
import kr.hs.dgsw.towerofhanoi.global.jwt.token.JwtAuthenticationToken;
import kr.hs.dgsw.towerofhanoi.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;

        Claims claims = jwtUtils.parseAccessToken(jwtAuthenticationToken.getToken());

        String username = claims.getSubject();
        List<GrantedAuthority> authorities = getGrantedAuthorities(claims);

        return new JwtAuthenticationToken(authorities, username, null);
    }

    private List<GrantedAuthority> getGrantedAuthorities(Claims claims) {

        List<String> roles = (List<String>) claims.get("roles");
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(() -> role);
        }
        
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
