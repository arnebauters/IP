package be.ucll.gerechten;

import be.ucll.gerechten.model.DagMenu;
import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.TypeGerecht;

import java.time.LocalDate;

public class MenuBuilder {
    private LocalDate date;
    private Gerecht soep, veggie, dagschotel;

    private MenuBuilder() {
    }

    public static MenuBuilder aMenu () {
        return new MenuBuilder();
    }

    public static MenuBuilder anOkMenu () {
        return aMenu().withDate(LocalDate.parse("2010-10-10")).withDagschotel(GerechtBuilder.aDagschotel().build()).withSoep(GerechtBuilder.aSoep().build()).withVeggie(GerechtBuilder.aVeggie().build());
    }

    public MenuBuilder withDate (LocalDate date) {
        this.date = date;
        return this;
    }

    public MenuBuilder withSoep (Gerecht gerecht) {
        this.soep = gerecht;
        return this;
    }

    public MenuBuilder withVeggie (Gerecht gerecht) {
        this.veggie = gerecht;
        return this;
    }

    public MenuBuilder withDagschotel (Gerecht gerecht) {
        this.dagschotel = gerecht;
        return this;
    }

    public DagMenu build() {
        DagMenu dagMenu = new DagMenu();
        dagMenu.setDate(date);
        dagMenu.setVeggie(this.veggie);
        dagMenu.setDagschotel(this.dagschotel);
        dagMenu.setSoep(this.soep);
        return dagMenu;
    }
}

