package pl.put.poznan.transformer.composite;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.put.poznan.transformer.logic.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Level extends Location {
    private List<Room> listOfRooms;

    public Level(@JsonProperty("id") int id,
                 @JsonProperty("name") String name,
                 @JsonProperty("listOfRooms") Room... rooms) {
        super(id, name);
        listOfRooms = new ArrayList<>();
        listOfRooms.addAll(List.of(rooms));
    }

    public Level(int id, String name) {
        super(id, name);
        listOfRooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        listOfRooms.add(room);
    }


    @Override
    public float calculateArea() {
        float areaResult = 0;
        for (Room i : listOfRooms) {
            areaResult += i.getArea();
        }
        return areaResult;
    }

    @Override
    public float calculateCubature() {
        float cubatureResult = 0;
        for (Room i : listOfRooms) {
            cubatureResult += i.getCube();
        }
        return cubatureResult;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLevel(this);
    }

    public List<Room> getlistOfRooms() {
        return listOfRooms;
    }

}