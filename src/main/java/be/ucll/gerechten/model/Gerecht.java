package be.ucll.gerechten.model;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;

public class Gerecht {

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 50)
    private String name;

    @DecimalMin("0.1")
    @DecimalMax("10.00")
    private double price;

    @NotNull
    private String type;


    /*public Gerecht(double price, String name) {
        this.price = price;
        this.name = name;
    }*/

    public Gerecht(double price, String name, String type) {
        this.price = price;
        this.name = name;
        setType(type);
    }

    private void setType(String type) {
        boolean found = false;
        for (TypeGerecht t : TypeGerecht.values()) {
            if (t.toString().equals(type)) {
                this.type = type;
                found = true;
            }
        }
        if (!found)
        throw new IllegalArgumentException("Geen geldig type gerecht");
    }

    public String getType(){
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
