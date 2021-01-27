package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Room;

/**
 * Class that implements Visitor to visit building and calculate its area.
 */
public class AreaVisitor implements Visitor {

    private float area;
    private int findId;

    public AreaVisitor(int findId) {
        this.findId = findId;
    }

    public float getArea() {
        return area;
    }
    /**
     *Method that make calculation on specific room
     * @param room Room object on which visitor make area calculation
     */
    @Override
    public void visitRoom(Room room) {
        if (room.getId() == findId) {
            area = room.calculateArea();
        }
    }

    /**
     *Method that make calculation on specific level
     * @param level Level object on which visitor make area calculation
     */
    @Override
    public void visitLevel(Level level) {
        if (level.getId() == findId) {
            area = level.calculateArea();
        } else {
            for (Room room : level.getlistOfRooms()) {
                visitRoom(room);
            }
        }
    }
    /**
     *Method that make calculation on specific Building
     * @param building Build object on which visitor make area calculation
     */
    @Override
    public void visitBuilding(Building building) {
        if (building.getId() == findId) {
            area = building.calculateArea();
        } else {
            for (Level level : building.getLevels()) {
                visitLevel(level);
            }
        }
    }
}