package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Room;

public class LightPowerVisitor implements Visitor {

    private float lightPower;
    private int findId;

    public LightPowerVisitor(int findId) {
        this.findId = findId;
    }

    public float getLightPower() {
        return lightPower;
    }

    @Override
    public void visitRoom(Room room) {
        if (room.getId() == findId) {
            lightPower = room.calculateLightPower();
        }
    }

    @Override
    public void visitLevel(Level level) {
        if (level.getId() == findId) {
            lightPower = level.calculateLightPower();
        } else {
            for (Room room : level.getlistOfRooms()) {
                visitRoom(room);
            }
        }
    }

    @Override
    public void visitBuilding(Building building) {
        if (building.getId() == findId) {
            lightPower = building.calculateLightPower();
        } else {
            for (Level level : building.getLevels()) {
                visitLevel(level);
            }
        }
    }

}
