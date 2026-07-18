package gb.gestione_eventi.repositories;

import gb.gestione_eventi.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}
