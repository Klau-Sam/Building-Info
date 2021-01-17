package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Room;

public class HeatingEnergyVisitor implements Visitor {

    private float HeatingEnergy;
    private int findId;

    public HeatingEnergyVisitor(int findId) {
        this.findId = findId;
    }

    public float getHeatingEnergy() {
        return HeatingEnergy;
    }

    @Override
    public void visitRoom(Room room) {
        if (room.getId() == findId) {
            HeatingEnergy = room.calculateHeatingEnergy();
        }
    }

    @Override
    public void visitLevel(Level level) {
        if (level.getId() == findId) {
            HeatingEnergy = level.calculateHeatingEnergy();
        } else {
            for (Room room : level.getlistOfRooms()) {
                visitRoom(room);
            }
        }
    }

    @Override
    public void visitBuilding(Building building) {
        if (building.getId() == findId) {
            HeatingEnergy = building.calculateHeatingEnergy();
        } else {
            for (Level level : building.getLevels()) {
                visitLevel(level);
            }
        }
    }
}