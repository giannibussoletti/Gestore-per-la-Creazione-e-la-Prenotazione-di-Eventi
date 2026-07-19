package gb.gestione_eventi.controllers;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.payloads.UserBookingResponseDTO;
import gb.gestione_eventi.payloads.UserEventResponseDTO;
import gb.gestione_eventi.services.BookingService;
import gb.gestione_eventi.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private EventService eventService;
    private BookingService bookingService;

    @GetMapping("/me/events")
    @PreAuthorize("hasAnyAuthority('ORGANIZZATORE')")
    public UserEventResponseDTO findEventByUser(@AuthenticationPrincipal User user) {
        return new UserEventResponseDTO(this.eventService.findEventByUser(user));
    }

    @PreAuthorize("hasAnyAuthority('UTENTE')")
    @GetMapping("/me/bookings")
    public UserBookingResponseDTO findBookingsByUser(@AuthenticationPrincipal User user) {
        List<Booking> bookingList = this.bookingService.findAllBookingsByUser(user);
        return new UserBookingResponseDTO(bookingList);
    }

}
