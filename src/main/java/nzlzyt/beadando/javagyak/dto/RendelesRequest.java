package nzlzyt.beadando.javagyak.dto;

import jakarta.persistence.*;
import nzlzyt.beadando.javagyak.entity.Pizza;

import java.util.Date;

public class RendelesRequest {
    private String pizzanev;
    private Integer darab;
    private String felvetel;
    private String kiszallitas;

    public RendelesRequest() {
    }

    public RendelesRequest(String pizzanev, Integer darab, String felvetel, String kiszallitas) {
        this.pizzanev = pizzanev;
        this.darab = darab;
        this.felvetel = felvetel;
        this.kiszallitas = kiszallitas;
    }

    public String getPizzanev() {
        return pizzanev;
    }

    public void setPizzanev(String pizzanev) {
        this.pizzanev = pizzanev;
    }

    public Integer getDarab() {
        return darab;
    }

    public void setDarab(Integer darab) {
        this.darab = darab;
    }

    public String getFelvetel() {
        return felvetel;
    }

    public void setFelvetel(String felvetel) {
        this.felvetel = felvetel;
    }

    public String getKiszallitas() {
        return kiszallitas;
    }

    public void setKiszallitas(String kiszallitas) {
        this.kiszallitas = kiszallitas;
    }
}
