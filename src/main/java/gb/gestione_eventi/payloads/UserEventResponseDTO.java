package gb.gestione_eventi.payloads;

import gb.gestione_eventi.entities.Event;

import java.util.List;

public record UserEventResponseDTO(List<Event> events) {
}
