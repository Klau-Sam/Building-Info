package pl.put.poznan.transformer.composite;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.put.poznan.transformer.logic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Building extends Location {

    private List<Level> listOflevels;
    private float landTax;

    public Building(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JsonProperty("landTax") float landTax,
                    @JsonProperty("listOflevels") Level... levels) {
        super(id, name);
        this.landTax = landTax;
        listOflevels = new ArrayList<>();
        listOflevels.addAll(List.of(levels));
    }

    public Building(int id, String name, float landTax) {
        super(id, name);
        this.landTax = landTax;
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
    public float calculateHeatingEnergy() {
        float heatingResult = 0;
        for (Level i : listOflevels) {
            heatingResult += i.calculateHeatingEnergy();
        }
        return listOflevels.size() == 0 ? 0.0f : heatingResult / listOflevels.size();
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
            cubatureResult += i.calculateCubature() / listOflevels.size();
        }
        return cubatureResult;
    }

    @Override
    public float calculateTaxes() {
        float sumOfTaxes = 0;
        for (Level i : listOflevels) {
            sumOfTaxes += i.calculateTaxes();
            if (i.getId() == 0) {
                sumOfTaxes += i.calculateArea() * landTax;
            }
        }
        return sumOfTaxes;
    }

    @Override
    public float calculateLightPower() {
        float lightResult = 0;
        for (Level i : listOflevels) {
            lightResult += i.calculateLightPower();
        }
        return listOflevels.size() == 0 ? 0.0f : lightResult/listOflevels.size();
    }

}