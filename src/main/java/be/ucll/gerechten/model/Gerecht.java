package be.ucll.gerechten.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Gerecht {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 15)
    private String name;

    @DecimalMin("0.1")
    @DecimalMax("10.00")
    private double price;

    @NotNull
    private TypeGerecht type;


    public Gerecht() {
    }

    public Gerecht(String name, TypeGerecht type, double price) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setType(TypeGerecht type) {
        this.type = type;
    }


    public TypeGerecht getType() {
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
