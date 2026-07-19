package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.exceptions.NotFoundException;
import gb.gestione_eventi.exceptions.ValidationException;
import gb.gestione_eventi.payloads.EventDTO;
import gb.gestione_eventi.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EventService {

    private EventRepository eventRepository;
    private UserService userService;


    public Event save(User user, EventDTO body) {
        if (!body.data().isAfter(LocalDate.now().plusDays(2)))
            throw new ValidationException("La data deve essere di almeno 3 giorni avanti rispetto ad oggi");
        User found = this.userService.findById(user.getId());
        return this.eventRepository.save(new Event(body.nome(), body.descrizione(), body.luogo(), body.data(), body.postiDisponibili(), found));
    }

    public Page<Event> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.eventRepository.findAll(pageable);
    }

    public Event findById(UUID id) {
        return this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun evento trovato con questo id"));
    }

    public List<Event> findEventByUser(User user) {
        return this.eventRepository.findEventByUser(user);
    }

    public void findEventByUserAndDelete(User user, UUID id) {
        List<Event> eventList = this.findEventByUser(user);
        if (eventList.stream().noneMatch(event -> event.getId() == id))
            throw new NotFoundException("Nessun Evento trovato");
        Event deleting = this.findById(id);
        this.eventRepository.delete(deleting);
    }

    public void updateSeatsEvent(Event event) {
        this.eventRepository.save(event);
    }

    public void updateEvent(User user, UUID eventId, EventDTO body) {
        List<Event> eventList = this.findEventByUser(user);
        if (eventList.stream().noneMatch(event -> event.getId().equals(eventId)))
            throw new NotFoundException("Nessun Evento trovato bla");

        Event found = this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException("Nessun Evento trovato"));
        found.setPostiDisponibili(body.postiDisponibili());
        found.setData(body.data());
        found.setDescrizione(body.descrizione());
        found.setLuogo(body.luogo());
        found.setNome(body.nome());
        this.eventRepository.save(found);

    }
}
