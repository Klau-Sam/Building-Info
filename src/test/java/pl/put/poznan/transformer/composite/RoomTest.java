package pl.put.poznan.transformer.composite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.logic.AreaVisitor;
import pl.put.poznan.transformer.logic.Visitor;
import static org.junit.jupiter.api.Assertions.*;


class RoomTest {
    private static Room room;

    @BeforeAll
    static void beforeAll() {
        room = new Room(777, "Test Room", 50.0f, 30.0f, 40.0f, 50.0f,0.85f);
    }

    @Test
    void calculateAreaTest() {
        assertEquals(room.calculateArea(), 30.0f);
    }

    @Test
    void calculateCubatureTest() {
        assertEquals(room.calculateCubature(), 50.0f);
    }

    @Test
    void calculateHeatingEnergyTest() {
        assertEquals(room.calculateHeatingEnergy(), 40.0f/50.0f);
    }

    @Test
    void acceptTest() {
        AreaVisitor visitor = new AreaVisitor(777);
        room.accept(visitor);
        assertEquals(visitor.getArea(), 30.0f);
    }

    @Test
    void calculateTaxesTest() {
        assertEquals(room.calculateTaxes(), 12.75f);
    }

}