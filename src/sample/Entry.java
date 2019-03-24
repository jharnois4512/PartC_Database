package sample;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;



public class Entry {
    private StringProperty nodeid;
    private IntegerProperty xcoord;
    private IntegerProperty ycoord;
    private StringProperty floor;
    private StringProperty building;
    private StringProperty nodeType;
    private StringProperty longName;
    private StringProperty shortName;

    public Entry(){
        this.nodeid = new SimpleStringProperty();
        this.xcoord = new SimpleIntegerProperty();
        this.ycoord = new SimpleIntegerProperty();
        this.floor = new SimpleStringProperty();
        this.building = new SimpleStringProperty();
        this.nodeType = new SimpleStringProperty();
        this.longName = new SimpleStringProperty();
        this.shortName = new SimpleStringProperty();
    }





    public StringProperty nodeidProperty() {
        return nodeid;
    }

    public IntegerProperty xcoordProperty() {
        return xcoord;
    }

    public IntegerProperty ycoordProperty() {
        return ycoord;
    }

    public StringProperty floorProperty() {
        return floor;
    }

    public StringProperty buildingProperty() {
        return building;
    }

    public StringProperty nodeTypeProperty() {
        return nodeType;
    }

    public StringProperty longNameProperty() {
        return longName;
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    public String getNodeid() {
        return nodeidProperty().get();
    }

    public int getXcoord() {
        return xcoordProperty().get();
    }

    public int getYcoord() {
        return ycoord.get();
    }

    public String getFloor() {
        return floor.get();
    }

    public String getBuilding() {
        return building.get();
    }

    public String getNodeType() {
        return nodeType.get();
    }

    public String getLongName() {
        return longNameProperty().get();
    }

    public String getShortName() {
        return shortName.get();
    }

    public void setNodeid(String nodeid) {
        this.nodeidProperty().set(nodeid);
    }

    public void setXcoord(int xcoord) {
        this.xcoordProperty().set(xcoord);
    }

    public void setYcoord(int ycoord) {
        this.ycoord.set(ycoord);
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public void setBuilding(String building) {
        this.building.set(building);
    }

    public void setNodeType(String nodeType) {
        this.nodeType.set(nodeType);
    }

    public void setLongName(String longName) {
        this.longNameProperty().set(longName);
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }


}
