package gb.gestione_eventi.controllers;

import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.ResponseDTO;
import gb.gestione_eventi.payloads.UserDTO;
import gb.gestione_eventi.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO save(@RequestBody @Validated UserDTO body, BindingResult valid) {
        if (valid.hasErrors()) {
            List<String> errorsMessage = valid.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            errorsMessage.forEach(System.out::println);
            throw new ValidationException(errorsMessage);
        }
        User newUser = this.userService.save(body);
        return new ResponseDTO("Registrazione avvenuta con successo", newUser.getId(), LocalDateTime.now());

    }
}
