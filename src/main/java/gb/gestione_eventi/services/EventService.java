package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.EventDTO;
import gb.gestione_eventi.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;


    public Event save(@AuthenticationPrincipal User user, EventDTO body) {
        if (!body.data().isAfter(LocalDate.now().plusDays(2)))
            throw new ValidationException("La data deve essere di almeno 3 giorni avanti rispetto ad oggi");

        return this.eventRepository.save(new Event(body.nome(), body.descrizione(), body.luogo(), body.data(), body.postiDisponibili(), user));

    }
}
