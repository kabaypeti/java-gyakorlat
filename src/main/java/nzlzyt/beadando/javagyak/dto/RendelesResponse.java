package nzlzyt.beadando.javagyak.dto;

public class RendelesResponse {

    private Long id;
    private String pizzanev;
    private Integer darab;
    private String felvetel;
    private String kiszallitas;

    public RendelesResponse() {
    }

    public RendelesResponse(Long id, String pizzanev, Integer darab, String felvetel, String kiszallitas) {
        this.id = id;
        this.pizzanev = pizzanev;
        this.darab = darab;
        this.felvetel = felvetel;
        this.kiszallitas = kiszallitas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
