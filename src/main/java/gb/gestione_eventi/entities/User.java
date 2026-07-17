package gb.gestione_eventi.entities;

import gb.gestione_eventi.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "users")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false)
    private String password;
    @Column(name = "avatar_url", nullable = false)
    private String avatarURL;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String name, String surname, String mail, String password, LocalDate birthDate, Role role) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.avatarURL = "https://placehold.co/200?text=" + this.name;
        this.birthDate = birthDate;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.mail;
    }
}

