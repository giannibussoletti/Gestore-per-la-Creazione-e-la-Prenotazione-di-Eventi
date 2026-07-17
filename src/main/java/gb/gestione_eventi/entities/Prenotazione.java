package gb.gestione_eventi.entities;

import gb.gestione_eventi.enums.StatoPrenotazione;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Evento evento;
    @ManyToOne
    @JoinColumn(name = "prenotante", nullable = false)
    private User user;
    @Column(name = "stato_prenotazione", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPrenotazione statoPrenotazione;
    @Column(name = "data_prenotazione", nullable = false)
    private LocalDate dataPrenotazione;
    @Column(name = "posti_prenotati", nullable = false)
    private int postiPrenotati;


    public Prenotazione(User user, Evento evento, int postiPrenotati) {
        this.user = user;
        this.evento = evento;
        this.postiPrenotati = postiPrenotati;
        this.statoPrenotazione = StatoPrenotazione.ATTIVA;
        this.dataPrenotazione = LocalDate.now();
    }
}
