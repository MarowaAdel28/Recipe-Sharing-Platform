package gov.iti.jets.enums;

import java.lang.annotation.Annotation;

public enum CategoryEnum {
    MainDish("mainDish",1),
    Soup("soup",2),
    Breakfast("breakfast",3),
    Dessert("dessert",4),
    Drink("drink",5);

    private final int id;
    private final String name;

    CategoryEnum(String name, int id) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
