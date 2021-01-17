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
     * location's name
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
     * Calculates location's area
     * @return area
     */
    public abstract float calculateArea();

    /**
     * Calculates location's area
     * @return cubature
     */
    public abstract float calculateCubature();

    /**
     * Lets the Visitor to use this class
     * @param visitor visitor that wants to calculate sth
     */
    public abstract void accept(Visitor visitor);

    /**
     * Calculates location's heating cost per m^3
     * @return heatingCost
     */
    public abstract float calculateHeatingEnergy();
    /**
     * Calculates location's taxes
     * @return sumOfTaxes
     */

    public abstract float calculateTaxes();
    /**
     * Calculates location's lighting power per m^3
     * @return lightResult
     */

    public abstract float calculateLightPower();
}