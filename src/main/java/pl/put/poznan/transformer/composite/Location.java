package pl.put.poznan.transformer.composite;


import pl.put.poznan.transformer.logic.Visitor;

/**
 * Abstract class that represent the object in building.
 * Classes Room,Level and Building inherit from it.
 */
public abstract class Location {
    /**
     * id number that identifies every location
     */
    private final int id;
    /**
     * name of location
     */
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

    /**
     * Calculates area of location
     * @return area
     */
    public abstract float calculateArea();

    /**
     * Calculates cubature of location
     * @return cubature
     */
    public abstract float calculateCubature();

    /**
     * Lets the Visitor to use this class
     * @param visitor visitor that wants to calculate sth
     */
    public abstract void accept(Visitor visitor);
}
