package be.ucll.gerechten.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
public class DagMenu {

    @NotNull
    private String day;

    @Id
    @NotNull
    private String date;

    @Valid
    @NotNull
    private Gerecht soep;

    @Valid
    @NotNull
    private Gerecht veggie;
    @Valid
    @NotNull
    private Gerecht dagschotel;

    public DagMenu(){}

    public DagMenu(String day, String date){
        this.day = day;
        this.date = date;
    }

    public DagMenu(String day, String date, Gerecht soep, Gerecht dagschotel, Gerecht veggie){
        this(day, date);
        this.soep = soep;
        this.veggie = veggie;
        this.dagschotel = dagschotel;
    }

    public String getDay() {
        return day;
    }

    private void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public void setSoep(Gerecht soep){
        if (!(soep.getType().equals(TypeGerecht.soep))){
            throw new IllegalArgumentException();
        }
       this.soep = soep;
    }

    public void setVeggie(Gerecht veggie){
        if (!(veggie.getType().equals(TypeGerecht.veggie))){
            throw new IllegalArgumentException();
        }
        this.veggie = veggie;
    }

    public void setDagschotel(Gerecht dagschotel){
        if (!(dagschotel.getType().equals(TypeGerecht.dagschotel))){
            throw new IllegalArgumentException();
        }
        this.dagschotel = dagschotel;
    }

    public Gerecht getSoep(){
        return this.soep;
    }

    public Gerecht getVeggie(){ return this.veggie;
    }

    public Gerecht getDagschotel(){
        return this.dagschotel;
    }

}
