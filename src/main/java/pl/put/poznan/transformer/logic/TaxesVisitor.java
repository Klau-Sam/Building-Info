package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Room;

public class TaxesVisitor implements Visitor {

    private float sumOfTaxes;
    private int findId;

    public TaxesVisitor(int findId) {
        this.findId = findId;
    }

    public float getSumOfTaxes() {
        return sumOfTaxes;
    }

    @Override
    public void visitRoom(Room room) {
        if (room.getId() == findId) {
            sumOfTaxes = room.calculateTaxes();
        }
    }

    @Override
    public void visitLevel(Level level) {
        if (level.getId() == findId) {
            sumOfTaxes = level.calculateTaxes();
        } else {
            for (Room room : level.getlistOfRooms()) {
                visitRoom(room);
            }
        }
    }

    @Override
    public void visitBuilding(Building building) {
        if (building.getId() == findId) {
            sumOfTaxes = building.calculateTaxes();
        } else {
            for (Level level : building.getLevels()) {
                visitLevel(level);
            }
        }
    }
}