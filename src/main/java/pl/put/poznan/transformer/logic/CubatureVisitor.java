package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Room;

/**
 * Class that implements Visitor to visit building and calculate its cubature.
 */
public class CubatureVisitor implements Visitor {

    private float cubature;
    private int findId;

    public CubatureVisitor(int findId) {
        this.findId = findId;
    }

    public float getCubature() {
        return cubature;
    }

    /**
     *Method that make calculation on specific room
     * @param room Room object on which visitor make cubature calculation
     */
    @Override
    public void visitRoom(Room room) {
        if (room.getId() == findId) {
            cubature = room.calculateCubature();
        }
    }
    /**
     *Method that make calculation on specific level
     * @param level Level object on which visitor make cubature calculation
     */
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
    /**
     *Method that make calculation on specific Building
     * @param building Build object on which visitor make cubature calculation
     */
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