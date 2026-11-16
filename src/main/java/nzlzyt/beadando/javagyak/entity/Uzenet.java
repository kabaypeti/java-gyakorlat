package nzlzyt.beadando.javagyak.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "uzenet")
public class Uzenet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String felhasznalo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String uzenet;

    @Column(nullable = false)
    private LocalDateTime datum;

    public Uzenet() {}

    public Uzenet(String felhasznalo, String uzenet, LocalDateTime datum) {
        this.felhasznalo = felhasznalo;
        this.uzenet = uzenet;
        this.datum = datum;
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getFelhasznalo() {return felhasznalo;}

    public void setFelhasznalo(String felhasznalo) {this.felhasznalo = felhasznalo;}

    public String getUzenet() {return uzenet;}

    public void setUzenet(String uzenet) {this.uzenet = uzenet;}

    public LocalDateTime getDatum() {return datum;}

    public void setDatum(LocalDateTime datum) { this.datum = datum; }
}
