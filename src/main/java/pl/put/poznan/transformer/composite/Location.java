package pl.put.poznan.transformer.composite;


import pl.put.poznan.transformer.logic.Visitor;

public abstract class Location {
    private final int id;
    private final String name;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract float calculateArea();

    public abstract float calculateCubature();

    public abstract void accept(Visitor visitor);
}
