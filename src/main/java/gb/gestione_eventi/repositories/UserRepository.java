package gb.gestione_eventi.repositories;

import gb.gestione_eventi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByMail(String mail);

    Optional<User> findUserByMail(String mail);

}
