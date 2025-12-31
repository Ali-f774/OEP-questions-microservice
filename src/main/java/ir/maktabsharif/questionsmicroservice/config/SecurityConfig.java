package ir.maktabsharif.questionsmicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http){
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                ).oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(converter())
                        )
                );
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter converter(){
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {

            Set<GrantedAuthority> authorities = new HashSet<>();

            Map<String, Object> resourceAccess =
                    jwt.getClaim("resource_access");

            if (resourceAccess != null) {
                Map<String, Object> client =
                        (Map<String, Object>) resourceAccess.get("spring-backend");

                if (client != null) {
                    Collection<String> roles =
                            (Collection<String>) client.get("roles");

                    roles.forEach(role ->
                            authorities.add(
                                    new SimpleGrantedAuthority("ROLE_" + role)
                            )
                    );
                }
            }


            return authorities;
        });
        converter.setPrincipalClaimName("preferred_username");

        return converter;
    }

}

