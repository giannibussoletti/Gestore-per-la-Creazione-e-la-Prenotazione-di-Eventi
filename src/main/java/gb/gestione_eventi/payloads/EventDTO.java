package gb.gestione_eventi.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventDTO(
        @NotBlank(message = "Il campo non può essere lasciato vuoto")
        @Size(min = 2, max = 40, message = "Il nome deve essere fra un minimo di 2 lettere ed un massimo di 40")
        String nome,
        @NotBlank(message = "Il campo non può essere lasciato vuoto")
        @Size(max = 250, message = "La descrizione deve essere di massimo 250 caratteri")
        String descrizione,
        @NotBlank(message = "Il campo non può essere lasciato vuoto")
        @Size(max = 250, message = "Il nome del luogo deve essere di massimo 250 caratteri")
        String luogo,
        @Future(message = "la data non può essere precedente ai prossimi 3 giorni")
        LocalDate data,
        @Positive
        int postiDisponibili
) {
}
