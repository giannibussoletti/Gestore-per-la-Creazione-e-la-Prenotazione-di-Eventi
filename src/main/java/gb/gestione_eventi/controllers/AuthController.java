package gb.gestione_eventi.controllers;

import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.LoginDTO;
import gb.gestione_eventi.payloads.LoginResponseDTO;
import gb.gestione_eventi.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponseDTO userLogin(@RequestBody @Validated LoginDTO body, BindingResult valid) {
        if (valid.hasErrors()) {
            List<String> errorsMessage = valid.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            errorsMessage.forEach(System.out::println);
            throw new ValidationException(errorsMessage);
        }

        return new LoginResponseDTO(this.authService.userLogin(body));

    }
}
