package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.LoginDTO;
import gb.gestione_eventi.security.TokenToolkit;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserService userService;
    private PasswordEncoder bcrypt;
    private TokenToolkit tTool;

    public String userLogin(LoginDTO body) {
        User found = this.userService.findByMail(body.mail());
        if (!this.bcrypt.matches(body.password(), found.getPassword())) {
            throw new ValidationException("La password non corrisponde");
        }
        return this.tTool.tokenGenerator(found);

    }
}
