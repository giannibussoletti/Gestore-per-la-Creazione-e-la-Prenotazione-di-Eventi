package gb.gestione_eventi.controllers;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.BookingDTO;
import gb.gestione_eventi.payloads.PatchBookingDTO;
import gb.gestione_eventi.payloads.ResponseDTO;
import gb.gestione_eventi.services.BookingService;
import gb.gestione_eventi.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;
    private EventService eventService;


    @PostMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO save(@AuthenticationPrincipal User user, @Validated @RequestBody BookingDTO body, BindingResult valid, @PathVariable UUID eventId) {
        if (valid.hasErrors()) {
            List<String> errorsMessage = valid.getFieldErrors().stream().map((DefaultMessageSourceResolvable::getDefaultMessage)).toList();
            throw new ValidationException(errorsMessage);
        }
        Event BookingEvent = this.eventService.findById(eventId);
        Booking newBooking = this.bookingService.save(user, body, BookingEvent);
        return new ResponseDTO("Prenotazione avvenuta con successo", newBooking.getId(), LocalDateTime.now());

    }

    // TODO fare il patch mapping per cambiare lo stato di una prenotazione da Attivo ad annullato
    @PatchMapping("/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO findBookingAndUpdate(@AuthenticationPrincipal User user, @RequestBody PatchBookingDTO body, BindingResult valid) {
        if (valid.hasErrors()) {
            List<String> errorsMessage = valid.getFieldErrors().stream().map((DefaultMessageSourceResolvable::getDefaultMessage)).toList();
            throw new ValidationException(errorsMessage);

        }
    }
}
