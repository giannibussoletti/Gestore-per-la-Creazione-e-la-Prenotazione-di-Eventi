package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.enums.StatoPrenotazione;
import gb.gestione_eventi.exceptions.AlreadyExistsException;
import gb.gestione_eventi.exceptions.NotFoundException;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.BookingDTO;
import gb.gestione_eventi.payloads.PatchBookingDTO;
import gb.gestione_eventi.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private EventService eventService;
    private UserService userService;


    public Booking save(User user, BookingDTO body, Event event) {
        Event found = this.eventService.findById(event.getId());
        int postiTotali = found.getPostiDisponibili();
        int updatedSeats = (postiTotali - body.postiPrenotati());
        found.setPostiDisponibili(updatedSeats);
        this.eventService.updateEvent(found);


        Booking newBooking = new Booking(user, event, body.postiPrenotati());
        // TODO la prenotazione deve togliere il numero di posti prenotati dall'evento nel database
        return this.bookingRepository.save(newBooking);
    }

    public Booking findAndUpdate(User user, UUID bookingId, PatchBookingDTO body) {
        List<Booking> bookings = this.bookingRepository.findBookingsByUser(user);
        if (bookings.stream().noneMatch(booking -> booking.getId().equals(bookingId))) {
            throw new NotFoundException("Nessuna prenotazione trovata");
        }

        Booking savedBooking = this.bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("Nessuna prenotazione trovata con questo id"));
        StatoPrenotazione updateStato = null;
        switch (body.statoPrenotazione().toLowerCase()) {
            case "attiva" -> updateStato = StatoPrenotazione.ATTIVA;
            case "annullata" -> updateStato = StatoPrenotazione.ANNULLATA;
            default -> throw new ValidationException("La prenotazione può essere solo 'attiva' o 'annullata'");
        }


        if (updateStato == savedBooking.getStatoPrenotazione())
            throw new AlreadyExistsException("La prenotazione ha già lo stato richiesto");

        savedBooking.setStatoPrenotazione(updateStato);
        this.bookingRepository.save(savedBooking);
        return savedBooking;
    }
}
