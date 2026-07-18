package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.payloads.BookingDTO;
import gb.gestione_eventi.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private EventService eventService;

    public Booking save(User user, BookingDTO body, Event event) {
        Booking newBooking = new Booking(user, event, body.postiPrenotati());
        return this.bookingRepository.save(newBooking);
    }
}
