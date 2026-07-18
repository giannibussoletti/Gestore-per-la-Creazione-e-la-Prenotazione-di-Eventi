package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.NotFoundException;
import gb.gestione_eventi.payloads.BookingDTO;
import gb.gestione_eventi.payloads.PatchBookingDTO;
import gb.gestione_eventi.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;

    public Booking save(User user, BookingDTO body, Event event) {
        Booking newBooking = new Booking(user, event, body.postiPrenotati());
        return this.bookingRepository.save(newBooking);
    }

    public Booking findAndUpdate(User user, PatchBookingDTO){
        Booking savedBooking = this.bookingRepository.findBookingByUser(user).orElseThrow(()-> new NotFoundException("Nessuna prenotazione trovata con questo id"));
        // TODO check se la prenotazione ha già lo stato che l'utente sta richiedendo, lanciare errore
        // TODO se lo stato è diverso da quello richiesto aggiornalo con il setter
        savedBooking.setStatoPrenotazione();
    }
}
