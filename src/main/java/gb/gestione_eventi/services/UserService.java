package gb.gestione_eventi.services;

import gb.gestione_eventi.entities.User;
import gb.gestione_eventi.enums.Role;
import gb.gestione_eventi.exceptions.AlreadyExistsException;
import gb.gestione_eventi.exceptions.NotFoundException;
import gb.gestione_eventi.payloads.UserDTO;
import gb.gestione_eventi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder bcrypt;

    public User save(UserDTO body) {

        if (this.userRepository.existsByMail(body.mail())) {
            throw new AlreadyExistsException("Questa email è già registrata");
        }
        Role role = null;
        switch (body.role()) {
            case "utente" -> role = Role.UTENTE;
            case "organizzatore" -> role = Role.ORGANIZZATORE;
            default -> throw new WrongThreadException("I ruoli possono essere solo 'utente' o 'organizzatore'");
        }

        User newUser = new User(body.name(), body.surname(), body.mail(), this.bcrypt.encode(body.password()), body.birthDate(), role);
        this.userRepository.save(newUser);
        return newUser;
    }

    public User findByMail(String mail) {
        return this.userRepository.findUserByMail(mail).orElseThrow(() -> new NotFoundException("L'email non risulta registrata"));
    }

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("L'utente non è presente nel Database"));
    }
}
