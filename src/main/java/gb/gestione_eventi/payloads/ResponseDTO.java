package gb.gestione_eventi.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseDTO(String message, UUID id, LocalDateTime createdAt) {
}
