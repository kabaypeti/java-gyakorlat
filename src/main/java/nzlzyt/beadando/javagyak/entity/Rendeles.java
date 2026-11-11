package nzlzyt.beadando.javagyak.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name="rendeles")
@Table(name="rendeles")
public class Rendeles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "pizzanev", referencedColumnName = "nev",  nullable = false)
    private Pizza pizza;
    @Column(nullable = false)
    private Integer darab;
    @Column(nullable = false)
    private Date felvetel;
    @Column(nullable = false)
    private Date kiszallitas;

    @PreRemove
    private void preRemove() {
        this.pizza = null;
    }

    public Rendeles() {
    }

    public Rendeles(Pizza pizza, Integer darab, Date felvetel, Date kiszallitas) {
        this.pizza = pizza;
        this.darab = darab;
        this.felvetel = felvetel;
        this.kiszallitas = kiszallitas;
    }

    public Long getId() {
        return id;
    }

    public void  setId(Long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getDarab() {
        return darab;
    }

    public void setDarab(Integer darab) {
        this.darab = darab;
    }

    public Date getFelvetel() {
        return felvetel;
    }

    public void setFelvetel(Date felvetel) {
        this.felvetel = felvetel;
    }

    public Date getKiszallitas() {
        return kiszallitas;
    }

    public void setKiszallitas(Date kiszallitas) {
        this.kiszallitas = kiszallitas;
    }
}
