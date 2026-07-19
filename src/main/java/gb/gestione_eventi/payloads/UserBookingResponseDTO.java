package gb.gestione_eventi.payloads;

import gb.gestione_eventi.entities.Booking;

import java.util.List;

public record UserBookingResponseDTO(List<Booking> bookingList) {
}
