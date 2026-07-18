package gb.gestione_eventi.repositories;

import gb.gestione_eventi.entities.Booking;
import gb.gestione_eventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    Optional<Booking> findBookingByUser(User user);
}
