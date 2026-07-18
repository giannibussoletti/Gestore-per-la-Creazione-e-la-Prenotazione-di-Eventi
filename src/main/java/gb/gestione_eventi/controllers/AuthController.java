package gb.gestione_eventi.controllers;

import gb.gestione_eventi.payloads.LoginDTO;
import gb.gestione_eventi.payloads.LoginResponseDTO;
import gb.gestione_eventi.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LoginResponseDTO userLogin(@RequestBody @Validated LoginDTO body) {
        return new LoginResponseDTO(this.authService.userLogin(body));

    }
}
