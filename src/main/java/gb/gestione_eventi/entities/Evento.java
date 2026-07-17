package gb.gestione_eventi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "eventi")
public class Evento {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String luogo;
    @Column(nullable = false)
    private String descrizione;
    @Column(nullable = false)
    private LocalDate data;
    @Column(name = "posti_disponibili", nullable = false)
    private int postiDisponibili;
    @ManyToOne
    @JoinColumn(name = "creatore", nullable = false)
    private User user;


    public Evento(String nome, String descrizione, String luogo, LocalDate data, int postiDisponibili, User user) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.luogo = luogo;
        this.data = data;
        this.postiDisponibili = postiDisponibili;
        this.user = user;
    }
}
