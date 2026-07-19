package gb.gestione_eventi.controllers;

import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.payloads.UserBookingsResponseDTO;
import gb.gestione_eventi.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private EventService eventService;

    @GetMapping("/me/events")
    public UserBookingsResponseDTO findEventByUser(@AuthenticationPrincipal User user) {
        return new UserBookingsResponseDTO(this.eventService.findEventByUser(user));
    }


}
