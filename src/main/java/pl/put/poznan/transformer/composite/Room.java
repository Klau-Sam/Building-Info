package pl.put.poznan.transformer.composite;

import pl.put.poznan.transformer.logic.Visitor;

public class Room extends Location {

    private float cube;
    private float area;
    private float heating;
    private float light;
    private float propertyTax;

    public Room(int id, String name, float cube, float area, float heating, float light, float propertyTax) {
        super(id, name);
        this.cube = cube;
        this.area = area;
        this.heating = heating;
        this.light = light;
        this.propertyTax = propertyTax;
    }

    public float getCube() {
        return cube;
    }

    public float getArea() {
        return area;
    }

    public float getHeating() {
        return heating;
    }

    public float getLight() {
        return light;
    }

    public float getPropertyTax() {
        return propertyTax;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitRoom(this);
    }

    @Override
    public float calculateArea() {
        return area;
    }

    @Override
    public float calculateCubature() {
        return cube;
    }

    @Override
    public float calculateHeatingEnergy() {
        return cube == 0.0f ? 0.0f : heating / cube;
    }

    @Override
    public float calculateTaxes() {
        float sumOfTaxes = 0;
        float height = (area == 0.0f ? 0.0f : cube / area);
        if (height > 2.20) {
            sumOfTaxes += area * propertyTax;
        } else if (height > 1.40) {
            sumOfTaxes += area * propertyTax * 0.5;
        }
        return sumOfTaxes;
    }

    @Override
    public float calculateLightPower() {
        return area == 0.0f ? 0.0f : light / area;
    }
}