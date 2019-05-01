package be.ucll.gerechten.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;


@Entity
public class DagMenu {

    @Id
    @NotNull
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Gerecht soep;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Gerecht veggie;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Gerecht dagschotel;

    private String dayName; // MONDAY, TUESDAY, ...
    private int dayOfWeek; // 1 = monday
    private int yearAndWeekNumber; // year 2019 and week 7 => 201907

    public DagMenu() {
    }

    public DagMenu(LocalDate date, Gerecht soep, Gerecht dagschotel, Gerecht veggie) {
        setDate(date);
        setSoep(soep);
        setVeggie(veggie);
        setDagschotel(dagschotel);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
        setDayName(date.getDayOfWeek().toString());
        // need this to get the number of the week in the year - 1st week, 2nd week, ...
        TemporalField weekOfWeekBasedYear = WeekFields.ISO.weekOfWeekBasedYear();
        setYearAndWeekNumber(date.getYear(), date.get(weekOfWeekBasedYear));
        // need this to get the number of the day in the week - 1 = monday, 2 = tuesday, ...
        weekOfWeekBasedYear = WeekFields.ISO.dayOfWeek();
        setDayOfWeek(date.get(weekOfWeekBasedYear));
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName.charAt(0) + dayName.substring(1).toLowerCase(); // only first letter uppercase
    }

    public int getYearAndWeekNumber() {
        return yearAndWeekNumber;
    }

    public void setYearAndWeekNumber(int year, int weekNumber) {
        String y = String.valueOf(year);
        // make sure we have a zero in front of a 1 digit weekNumber
        String wn = String.format("%02d", weekNumber);
        // this puts the year and the week after each other:
        // eg. 201917 for year 2019 and week 17 or 201907 for year 2019 and week 7
        this.yearAndWeekNumber = Integer.parseInt(y + wn);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DagMenu) {
            DagMenu day = (DagMenu) o;
            if (this.getDate().equals(day.getDate())) return true;
        }
        return false;
    }

    //soep getter en setter
    public void setSoep(Gerecht g) {
        if (g == null || !g.getType().equals(TypeGerecht.soep)) {
            throw new IllegalArgumentException();
        }
        this.soep = g;
    }

    public Gerecht getSoep() {
        return soep;
    }

    //veggie getter en setter

    public void setVeggie(Gerecht g) {
        if (g == null || !g.getType().equals(TypeGerecht.veggie)) {
            throw new IllegalArgumentException();
        }
        this.veggie = g;
    }

    public Gerecht getVeggie() {
        return veggie;
    }

    //dagschotel getter en setter

    public void setDagschotel(Gerecht g) {
        if (g == null || !g.getType().equals(TypeGerecht.dagschotel)) {
            throw new IllegalArgumentException();
        }
        this.dagschotel = g;
    }

    public Gerecht getDagschotel() {
        return dagschotel;
    }
}
