package pl.put.poznan.transformer.composite;

import pl.put.poznan.transformer.logic.Visitor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Building extends Location {

    private List<Level> listOflevels;

    public Building(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JsonProperty("listOflevels") Level... levels) {
        super(id, name);
        listOflevels = new ArrayList<>();
        listOflevels.addAll(List.of(levels));
    }

    public Building(int id, String name) {
        super(id, name);
        listOflevels = new ArrayList<>();
    }

    public List<Level> getLevels() {
        return listOflevels;
    }

    public void setLevels(List<Level> levels) {
        this.listOflevels = levels;
    }

    public void addLevel(Level level) {
        listOflevels.add(level);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitBuilding(this);
    }

    @Override
    public float calculateArea() {
        float areaResult = 0;
        for (Level i : listOflevels) {
            areaResult += i.calculateArea();
        }
        return areaResult;
    }

    @Override
    public float calculateCubature() {
        float cubatureResult = 0;
        for (Level i : listOflevels) {
            cubatureResult += i.calculateCubature();
        }
        return cubatureResult;
    }

}