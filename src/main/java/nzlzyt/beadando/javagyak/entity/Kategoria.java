package nzlzyt.beadando.javagyak.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="kategoria")
public class Kategoria {

    @Id
    private String nev = "";
    @Column(nullable = false)
    private Integer ar;

    public Kategoria() {
    }

    public Kategoria(String nev, Integer ar) {
        this.nev = nev;
        this.ar = ar;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }
}
