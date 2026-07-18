package gb.gestione_eventi.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public record BookingDTO(
        @Positive
        @Min(value = 1, message = "Bisogna prenotare almeno un posto")
        @Max(value = 10, message = "non si possono prenotare più di 10 posti")
        int postiPrenotati) {
}
