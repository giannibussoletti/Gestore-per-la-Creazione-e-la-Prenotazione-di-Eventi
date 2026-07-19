package gb.gestione_eventi.security;

import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.UnauthorizedException;
import gb.gestione_eventi.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.UUID;

@Component

public class TokenFilter extends OncePerRequestFilter {

    private HandlerExceptionResolver resolver;
    private TokenToolkit tokenTools;
    private UserService userService;

    public TokenFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, TokenToolkit tokenTools, UserService userService) {
        this.resolver = resolver;
        this.tokenTools = tokenTools;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer "))
                throw new UnauthorizedException("Il token è mancante o non valido, ricontrolla di aver inserito il token corretto con 'Bearer ' davanti");
            String token = header.replace("Bearer ", "");
            this.tokenTools.tokenVerify(token);

            UUID id = this.tokenTools.extractId(token);
            User authUser = this.userService.findById(id);
            Authentication authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            resolver.resolveException(request, response, null, ex);
        }


        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
