package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;
import pl.put.poznan.transformer.composite.Building;
import pl.put.poznan.transformer.composite.Level;
import pl.put.poznan.transformer.composite.Location;
import pl.put.poznan.transformer.composite.Room;

import java.util.Arrays;


@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private static Building building = new Building(3, "Building");

    @GetMapping("/create")
    Building create() {
        Room room1 = new Room(313, "Dining room", 20, 112, 30, 10);
        Room room2 = new Room(323, "Kitchen", 15, 80, 25, 19);
        Room room3 = new Room(333, "Living room", 75, 210, 50, 60);
        Room room4 = new Room(343, "Bathroom", 13, 72, 26, 30);
        Room room5 = new Room(353, "Bedroom",  15, 90, 17, 13);
        Room room6 = new Room(363, "Office", 25, 150, 13, 21);
        Room room7 = new Room(373, "Shower room", 10, 66, 30, 8);
        Room room8 = new Room(383, "Library", 15, 77, 10, 33);
        Room room9 = new Room(383, "Attic", 32, 44, 8, 10);

        Level level0 = new Level(500, "Ground floor", room1, room2, room3, room4);
        Level level1 = new Level(501, "First floor", room5, room6, room7);
        Level level2 = new Level(502, "Second floor", room8);
        Level level3 = new Level(503, "Third floor", room9);

        building.addLevel(level0);
        building.addLevel(level1);
        building.addLevel(level2);
        building.addLevel(level3);

        return building;
    }

    @GetMapping("area/{id}")
    Obj returnArea(@PathVariable int id, @RequestBody Building building) {
        AreaVisitor areaVisitor = new AreaVisitor(id);
        building.accept(areaVisitor);
        logger.info("Request for area of " + id + " id");
        return new Obj(String.valueOf(areaVisitor.getArea()));
    }

    @GetMapping("/cube/{id}")
    Obj returnCubature(@PathVariable int id, @RequestBody Building building) {
        CubatureVisitor CubatureVisitor = new CubatureVisitor(id);
        building.accept(CubatureVisitor);
        logger.info("Request for cubature of " + id + " id");
        return new Obj(String.valueOf(CubatureVisitor.getCubature()));
    }

}


