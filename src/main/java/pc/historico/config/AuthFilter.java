package pc.historico.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.client.WebClient;
import pc.historico.dtos.VerifTokenResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String parameter = request.getParameter("token");

        WebClient webClient = WebClient.create("http://172.20.1.11:8080");

        VerifTokenResponse remoteToken = webClient.post()
                .uri("/verify/token?token=" + parameter)
                .retrieve()
                .onStatus(HttpStatus::isError,
                        clientResponse -> {
                            try {
                                throw new AuthenticationException("Error " + clientResponse.statusCode() + " al autenticar!");
                            } catch (AuthenticationException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .bodyToMono(VerifTokenResponse.class)
                .log(log.getName())
                .block();

        if (remoteToken.getMensaje().equals("Token Invalido")) {
            SecurityContextHolder.clearContext();
            log.debug("El Token " + remoteToken.getTokenEncript() + " ha expirado o no es válido.");
            filterChain.doFilter(request, response);
            return;
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(remoteToken.getRol()));

        User u = new User(remoteToken.getUsername(), remoteToken.getUsername(), roles);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(u, null,
                u.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);



    }
}
