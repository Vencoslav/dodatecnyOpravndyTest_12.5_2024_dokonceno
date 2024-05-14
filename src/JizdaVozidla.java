import java.time.LocalDate;

public class JizdaVozidla {
    private LocalDate datum;
    private Double benzin;
    private String jmeno;
    private Integer ujeteKilometry;
    private Integer typ;

    public JizdaVozidla(LocalDate datum, Double benzin,String jmeno,Integer ujeteKilometry,Integer typ){
        this.benzin = benzin;
        this.typ = typ;
        this.datum = datum;
        this.jmeno = jmeno;
        this.ujeteKilometry = ujeteKilometry;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Double getBenzin() {
        return benzin;
    }

    public String getJmeno() {
        return jmeno;
    }

    public Integer getUjeteKilometry() {
        return ujeteKilometry;
    }

    public Integer getTyp() {
        return typ;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setBenzin(Double benzin) {
        this.benzin = benzin;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setUjeteKilometry(Integer ujeteKilometry) {
        this.ujeteKilometry = ujeteKilometry;
    }

    public void setTyp(Integer typ) {
        this.typ = typ;
    }
}
