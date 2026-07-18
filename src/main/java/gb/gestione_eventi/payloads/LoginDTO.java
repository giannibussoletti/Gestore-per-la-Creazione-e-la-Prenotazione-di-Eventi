package gb.gestione_eventi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO(@NotBlank(message = "Il campo non può essere lasciato vuoto")
                       @Email(message = "L'email non rispetta i requisiti minimi")
                       String mail,
                       @NotBlank(message = "Il campo non può essere lasciato vuoto")
                       @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
                       String password) {
}
