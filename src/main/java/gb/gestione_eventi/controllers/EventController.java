package gb.gestione_eventi.controllers;

import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.EventDTO;
import gb.gestione_eventi.payloads.ResponseDTO;
import gb.gestione_eventi.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO save(@AuthenticationPrincipal User user, @RequestBody EventDTO body, BindingResult valid) {
        if (valid.hasErrors()) {
            List<String> errorsMessage = valid.getFieldErrors().stream().map((DefaultMessageSourceResolvable::getDefaultMessage)).toList();
            throw new ValidationException(errorsMessage);
        }

        Event save = this.eventService.save(user, body);
        return new ResponseDTO("Evento salvato correttamente", save.getId(), LocalDateTime.now());
    }

}
