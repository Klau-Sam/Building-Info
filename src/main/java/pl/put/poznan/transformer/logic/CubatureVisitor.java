package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Room;

public class CubatureVisitor implements Visitor {

    private float cubature;
    private int findId;

    public CubatureVisitor(int findId) {
        this.findId = findId;
    }

    public float getCubature() {
        return cubature;
    }

    @Override
    public void visitRoom(Room room) {
        if (room.getId() == findId) {
            cubature = room.calculateCubature();
        }
    }

    @Override
    public void visitLevel(Level level) {
        if (level.getId() == findId) {
            cubature = level.calculateCubature();
        } else {
            for (Room room : level.getlistOfRooms()) {
                visitRoom(room);
            }
        }
    }

    @Override
    public void visitBuilding(Building building) {
        if (building.getId() == findId) {
            cubature = building.calculateCubature();
        } else {
            for (Level level : building.getLevels()) {
                visitLevel(level);
            }
        }
    }
}