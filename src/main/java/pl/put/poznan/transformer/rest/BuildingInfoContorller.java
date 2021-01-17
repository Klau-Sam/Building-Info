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
import java.util.List;

/**
 * Class that manages the REST comunication
 */

@RestController
public class BuildingInfoContorller {
    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoContorller.class);
    private static Building building = new Building(3, "Building", 0.52f);

    /**
     * Create an example of building structure
     * @return Building object
     */
    @GetMapping("/create")
    Building create() {
        Room room1 = new Room(313, "Dining room", 112, 66, 30, 10,0.85f);
        Room room2 = new Room(323, "Kitchen", 80, 50, 25, 19,0.85f);
        Room room3 = new Room(333, "Living room", 75, 48, 50, 60,0.85f);
        Room room4 = new Room(343, "Bathroom", 100, 72, 26, 30,0.85f);
        Room room5 = new Room(353, "Bedroom",  50, 30, 17, 13,0.85f);
        Room room6 = new Room(363, "Office", 25, 21, 13, 21,0.85f);
        Room room7 = new Room(373, "Shower room", 10, 7, 30, 8,0.85f);
        Room room8 = new Room(383, "Library", 15, 11, 10, 33,0.85f);
        Room room9 = new Room(383, "Attic", 19, 12, 8, 10,0.85f);

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

    /**
     *Calculates area of the given Location
     * @param id id of Location
     * @param building Building, which contains location from the first parameter
     * @return calculated area
     */
    @GetMapping("area/{id}")
    Obj returnArea(@PathVariable int id, @RequestBody Building building) {
        AreaVisitor areaVisitor = new AreaVisitor(id);
        building.accept(areaVisitor);
        logger.info("Request for area of " + id + " id");
        return new Obj(String.valueOf(areaVisitor.getArea()));
    }
    /**
     *Calculates cubature of the given Location
     * @param id id of Location
     * @param building Building, which contains location from the first parameter
     * @return calculated cubature
     */
    @GetMapping("/cubature/{id}")
    Obj returnCubature(@PathVariable int id, @RequestBody Building building) {
        CubatureVisitor cubatureVisitor = new CubatureVisitor(id);
        building.accept(cubatureVisitor);
        logger.info("Request for cubature of " + id + " id");
        return new Obj(String.valueOf(cubatureVisitor.getCubature()));
    }

    @GetMapping("/lightPower/{id}")
    Obj returnLightPower(@PathVariable int id, @RequestBody Building building) {
        LightPowerVisitor lightPowerVisitor = new LightPowerVisitor(id);
        building.accept(lightPowerVisitor);
        logger.info("Request for light power of " + id + " id");
        return new Obj(String.valueOf(lightPowerVisitor.getLightPower()));
    }
    @GetMapping("/tax/{id}")
    Obj returnTax(@PathVariable int id, @RequestBody Building building) {
        TaxesVisitor  taxesVisitor= new TaxesVisitor(id);
        building.accept(taxesVisitor);
        logger.info("Request for tax of " + id + " id");
        return new Obj(String.valueOf(taxesVisitor.getSumOfTaxes()));
    }
    /**
     *
     * @param overheatingLimit a certain level of energy consumption
     * @param building Building on which calculations are performed
     * @return locations where energy consumption is higher that allowed level.
     */
    @GetMapping("/heatingOptimalize/{overheatingLimit}")
    List<Location> heatingOptimalizeLocations(@PathVariable float overheatingLimit, @RequestBody Building building) {
        HeatingOptimalizeVisitor visitor = new HeatingOptimalizeVisitor(overheatingLimit);
        building.accept(visitor);
        logger.info("Request for locations with avg. heating above " + overheatingLimit);
        return visitor.getOverheatedLocations();
    }

    /**
     * Calculates power used on heating per volume unit of specific Location
     * @param id id of Location to be examined
     * @param building Building on which calculations are performed
     * @return power used on heating per volume of Location provided as argument
     */
    @GetMapping("/heatingEnergy/{id}")
    Obj returnHeatingEnergy(@PathVariable int id, @RequestBody Building building) {
        HeatingEnergyVisitor HeatingEnergyVisitor = new HeatingEnergyVisitor(id);
        building.accept(HeatingEnergyVisitor);
        logger.info("Request for heating of " + id + " id");
        return new Obj(String.valueOf(HeatingEnergyVisitor.getHeatingEnergy()));
    }
}
