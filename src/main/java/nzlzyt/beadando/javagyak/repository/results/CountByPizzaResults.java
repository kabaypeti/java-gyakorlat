package nzlzyt.beadando.javagyak.repository.results;

public class CountByPizzaResults {

    private String pizzanev;
    private long darab;

    public CountByPizzaResults() {
    }

    public CountByPizzaResults(String pizzanev, long darab) {
        this.pizzanev = pizzanev;
        this.darab = darab;
    }

    public String getPizzanev() {
        return this.pizzanev;
    }

    public void setPizzanev(String pizzanev) {
        this.pizzanev = pizzanev;
    }

    public long getDarab() {
        return this.darab;
    }

    public void setDarab(long darab) {
        this.darab = darab;
    }
}
