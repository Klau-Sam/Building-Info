package pl.put.poznan.transformer.composite;

import pl.put.poznan.transformer.logic.Visitor;

public class Room extends Location{

    private float cube;
    private float area;
    private float heating;
    private float light;

    public Room(int id, String name, float cube, float area, float heating, float light){
        super(id,name);
        this.cube = cube;
        this.area= area;
        this.heating = heating;
        this.light=light;
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visitRoom(this);
    }
    @Override
    public float calculateArea() {
        return area;
    }

    @Override
    public float calculateCubature(){
        return cube;
    }
}