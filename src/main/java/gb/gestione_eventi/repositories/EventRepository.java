package gb.gestione_eventi.repositories;

import gb.gestione_eventi.entities.Event;
import gb.gestione_eventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findEventByUser(User user);
}
