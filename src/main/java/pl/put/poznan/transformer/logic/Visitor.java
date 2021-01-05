package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Location;
import pl.put.poznan.transformer.composite.Room;

/**
 * Interface that is implemented by AreaVisitor and CubatureVisitor
 */
public interface Visitor {

    /**
     *Method that make calculation on specific building
     * @param building Building object on which visitor make calculation
     */
    void visitBuilding(Building building);

    /**
     * Method that make calculation on specific room
     * @param room Room object on which visitor make calculation
     */
    void visitRoom(Room room);

    /**
     * Method that make calculation on specific level
     * @param level Level object on which visitor make calculation
     */
    void visitLevel(Level level);
}