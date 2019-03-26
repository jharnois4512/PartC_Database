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
        return ycoordProperty().get();
    }

    public String getFloor() {
        return floorProperty().get();
    }

    public String getBuilding() {
        return buildingProperty().get();
    }

    public String getNodeType() {
        return nodeTypeProperty().get();
    }

    public String getLongName() {
        return longNameProperty().get();
    }

    public String getShortName() {
        return shortNameProperty().get();
    }

    public void setNodeid(String nodeid) {
        this.nodeidProperty().set(nodeid);
    }

    public void setXcoord(int xcoord) {
        this.xcoordProperty().set(xcoord);
    }

    public void setYcoord(int ycoord) {
        this.ycoordProperty().set(ycoord);
    }

    public void setFloor(String floor) {
        this.floorProperty().set(floor);
    }

    public void setBuilding(String building) {
        this.buildingProperty().set(building);
    }

    public void setNodeType(String nodeType) {
        this.nodeTypeProperty().set(nodeType);
    }

    public void setLongName(String longName) {
        this.longNameProperty().set(longName);
    }

    public void setShortName(String shortName) {
        this.shortNameProperty().set(shortName);
    }


}
