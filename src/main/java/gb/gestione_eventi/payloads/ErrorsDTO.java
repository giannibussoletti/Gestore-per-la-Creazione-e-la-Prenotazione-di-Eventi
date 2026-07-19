package gb.gestione_eventi.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateTime) {
}
