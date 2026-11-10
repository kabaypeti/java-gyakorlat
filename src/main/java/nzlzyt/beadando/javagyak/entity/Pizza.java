package nzlzyt.beadando.javagyak.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="pizza")
public class Pizza {

    @Id
    private String nev;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "kategorianev", referencedColumnName = "nev", nullable = false)
    private Kategoria kategoria;
    @Column(nullable = false)
    private Boolean vegetarianus;

    @PreRemove
    private void preRemove() {
        this.kategoria = null;
    }

    public Pizza() {
    }

    public Pizza(String nev, Kategoria kategoria, Boolean vegetarianus) {
        this.nev = nev;
        this.kategoria = kategoria;
        this.vegetarianus = vegetarianus;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public Boolean getVegetarianus() {
        return vegetarianus;
    }

    public void setVegetarianus(Boolean vegetarianus) {
        this.vegetarianus = vegetarianus;
    }
}
