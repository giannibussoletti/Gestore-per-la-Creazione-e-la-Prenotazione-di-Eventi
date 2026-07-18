package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.NotFoundException;
import gb.gestione_eventi.payloads.BookingDTO;
import gb.gestione_eventi.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private EventService eventService;

    public Booking save(User user, BookingDTO body) {
        List<Event> eventList = this.eventService.findAllListed();
        if (eventList.size() > 1) {

        } else if (eventList.isEmpty()) {
            throw new NotFoundException("Nessun Evento a questo nome");

        }
        Booking newBooking = new Booking(user, eventList.getFirst(), body.postiPrenotati());
        return this.bookingRepository.save(newBooking);
    }
}
