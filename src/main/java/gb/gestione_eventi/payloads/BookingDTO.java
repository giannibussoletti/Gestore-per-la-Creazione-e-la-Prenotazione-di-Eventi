package gb.gestione_eventi.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BookingDTO(
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String event,
        @Positive
        @Size(min = 1, max = 10, message = "non si possono prenotare più di 10 posti")
        int postiPrenotati) {
}
