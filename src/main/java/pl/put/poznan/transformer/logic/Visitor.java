package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Location;
import pl.put.poznan.transformer.composite.Room;

public interface Visitor {

    void visitBuilding(Building building);

    void visitRoom(Room room);

    void visitLevel(Level level);
}
