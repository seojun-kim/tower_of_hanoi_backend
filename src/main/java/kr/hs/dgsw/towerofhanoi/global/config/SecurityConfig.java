package kr.hs.dgsw.towerofhanoi.global.config;

import kr.hs.dgsw.towerofhanoi.domain.member.service.MemberService;
import kr.hs.dgsw.towerofhanoi.global.jwt.exception.CustomExceptionEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationManagerConfig authenticationManagerConfig;
    private final CustomExceptionEntryPoint customExceptionEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(authenticationManagerConfig).and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/members/signup", "/members/login", "/members/issuedToken").permitAll()
                        .requestMatchers("/cleartime/list").permitAll()
                        .requestMatchers("/members/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                        .requestMatchers("/cleartime/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customExceptionEntryPoint)
                .and()
                .getOrBuild();
    }

    // <<Advanced>> Security Cors로 변경 시도
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // config.setAllowCredentials(true); // 이거 빼면 된다
        // https://gareen.tistory.com/66
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.setAllowedMethods(List.of("GET","POST","DELETE","PATCH","OPTION","PUT"));
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
