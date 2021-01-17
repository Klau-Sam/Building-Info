package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Location;
import pl.put.poznan.transformer.composite.Room;

import java.util.ArrayList;
import java.util.List;

public class HeatingOptimalizeVisitor implements Visitor {




    private float overheatingLimit;

    List<Location> overheatedLocations;

    public HeatingOptimalizeVisitor(float overheatingLimit) {
        this.overheatingLimit = overheatingLimit;
        overheatedLocations = new ArrayList<>();
    }

    @Override
    public void visitRoom(Room room) {
        if (room.calculateHeatingEnergy() > overheatingLimit) {
            overheatedLocations.add(room);
        }
    }

    @Override
    public void visitLevel(Level level) {
        if (level.calculateHeatingEnergy() > overheatingLimit) {
            overheatedLocations.add(level);
        }
        for (Room room : level.getlistOfRooms()) {
            visitRoom(room);
        }

    }

    @Override
    public void visitBuilding(Building building) {
        if (building.calculateHeatingEnergy() > overheatingLimit) {
            overheatedLocations.add(building);
        }
        for (Level level : building.getLevels()) {
            visitLevel(level);
        }
    }

    public List<Location> getOverheatedLocations() {
        return overheatedLocations;
    }
}
