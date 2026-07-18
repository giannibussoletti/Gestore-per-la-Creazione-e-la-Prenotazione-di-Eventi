package gb.gestione_eventi.payloads;

import jakarta.validation.constraints.NotBlank;

public record PatchBookingDTO(
        @NotBlank(message = "Il campo non può essere lasciato vuoto")
        String statoPrenotazione) {
}
