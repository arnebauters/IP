package be.ucll.gerechten.model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class DagMenu {

    @NotNull
    private DayOfWeek day;

    @NotNull
    private LocalDate date;

    @Valid
    private Gerecht soep;
    @Valid
    private Gerecht dagschotel;
    @Valid
    private Gerecht veggie;

    public DagMenu(DayOfWeek day, LocalDate date){
        this.day = day;
        this.date = date;
    }

    public DagMenu(DayOfWeek day, LocalDate date, Gerecht soep, Gerecht dagschotel, Gerecht veggie){
        this(day, date);
        this.soep = soep;
        this.dagschotel = dagschotel;
        this.veggie = veggie;
    }

    public DayOfWeek getDay() {
        return day;
    }

    private void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalDate getDate() {
        return date;
    }

    private void setDate(LocalDate date) {
        this.date = date;
    }

    public Gerecht getSoep() {
        return soep;
    }

    private void setSoep(Gerecht soep) {
        if (soep.getType().equals(TypeGerecht.soep.toString())) {
            this.soep = soep;
        }else {
            throw new IllegalArgumentException("Dit gerecht is geen soep");
        }
    }

    public Gerecht getDagschotel() {
        return dagschotel;
    }

    public void setDagschotel(Gerecht dagschotel) {
        if (dagschotel.getType().equals(TypeGerecht.dagschotel.toString())) {
            this.dagschotel = dagschotel;
        }else {
            System.out.println(dagschotel.getType());
            System.out.println(TypeGerecht.dagschotel);
            throw new IllegalArgumentException("Dit gerecht is geen dagschotel");
        }
    }

    public Gerecht getVeggie() {
        return veggie;
    }

    public void setVeggie(Gerecht veggie) {
        if (veggie.getType().equals(TypeGerecht.veggie.toString())) {
            this.veggie = veggie;
        }else {
            throw new IllegalArgumentException("Dit gerecht is geen veggie");
        }
    }

    public void voegSoepToe(Gerecht gerecht) {
        if (this.soep == null){
            setSoep(gerecht);
        }else {
            throw new IllegalArgumentException("Er is al een soep");
        }
    }

    public void voegDagschotelToe(Gerecht gerecht) {
        if (this.dagschotel == null){
            setDagschotel(gerecht);
        }else {
            throw new IllegalArgumentException("Er is al een dagschotel");
        }
    }

    public void voegVeggieToe(Gerecht gerecht) {
        if (this.veggie == null){
            setVeggie(gerecht);
        }else {
            throw new IllegalArgumentException("Er is al een veggie");
        }
    }
}