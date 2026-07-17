package gb.gestione_eventi.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UserDTO(@NotBlank(message = "Il campo non può essere lasciato vuoto")
                      @Size(min = 2, max = 40, message = "Il nome deve essere fra un minimo di 2 lettere ed un massimo di 40")
                      String name,
                      @Size(min = 2, max = 40, message = "Il cognonome deve essere fra un minimo di 2 lettere ed un massimo di 40")
                      @NotBlank(message = "Il campo non può essere lasciato vuoto")
                      String surname,
                      @NotBlank(message = "Il campo non può essere lasciato vuoto")
                      @Email(message = "L'email non rispetta i requisiti minimi")
                      String mail,
                      @NotBlank(message = "Il campo non può essere lasciato vuoto")
                      @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
                      String password,
                      @NotNull(message = "Il campo non può essere lasciato vuoto")
                      @Past(message = "La data di nascita deve essere antecedente ad oggi")
                      LocalDate birthDate,
                      @NotBlank(message = "Il campo non può essere lasciato vuoto")
                      String role) {
}
