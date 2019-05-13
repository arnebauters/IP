package be.ucll.gerechten;

import be.ucll.gerechten.model.Gerecht;
import be.ucll.gerechten.model.TypeGerecht;

public class GerechtBuilder {

    private String name;
    private double price;
    private TypeGerecht type;

    private GerechtBuilder() {
    }

    public static GerechtBuilder aGerecht () {
        return new GerechtBuilder();
    }

    public static GerechtBuilder anOkGerecht () {
        return aGerecht().withName("Spaghetti").withPrice(3).withType(TypeGerecht.dagschotel);
    }

    public static GerechtBuilder aSoep () {
        return aGerecht().withName("soep").withPrice(3).withType(TypeGerecht.soep);
    }

    public static GerechtBuilder aVeggie () {
        return aGerecht().withName("veggie").withPrice(3).withType(TypeGerecht.veggie);
    }

    public static GerechtBuilder aDagschotel () {
        return aGerecht().withName("Spaghetti").withPrice(3).withType(TypeGerecht.dagschotel);
    }

    public GerechtBuilder withName (String name) {
        this.name = name;
        return this;
    }

    public GerechtBuilder withPrice (double price) {
        this.price = price;
        return this;
    }

    public GerechtBuilder withType (TypeGerecht type) {
        this.type = type;
        return this;
    }

    public Gerecht build() {
        Gerecht gerecht = new Gerecht();
        gerecht.setName(name);
        gerecht.setPrice(this.price);
        gerecht.setType(this.type);
        return gerecht;
    }
}
