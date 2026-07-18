package gb.gestione_eventi.controllers;

import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.payloads.UserBookingsResponseDTO;
import gb.gestione_eventi.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private EventService eventService;

    @GetMapping("/me")
    public UserBookingsResponseDTO findEventByUser(@AuthenticationPrincipal User user) {
        return new UserBookingsResponseDTO(this.eventService.findEventByUser(user.getId()));
    }

    @DeleteMapping("/me/d/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findEventByUserAndDelete(@PathVariable UUID eventId) {
        this.eventService.findEventeByUserAndDelete(eventId);
    }


}
