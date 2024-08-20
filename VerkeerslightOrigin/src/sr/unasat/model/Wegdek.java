package sr.unasat.model;

import sr.unasat.service.serviceimpl.NodeCar;

public class Wegdek {
    private String wegdekNaam;
    private int sensor;
    private NodeCar cars;


    public Wegdek(String wegdekNaam, int sensor, NodeCar cars) {
        this.wegdekNaam = wegdekNaam;
        this.sensor = sensor;
        this.cars = cars;
    }

    public String getWegdekNaam() {
        return wegdekNaam;
    }

    public void setWegdekNaam(String wegdekNaam) {
        this.wegdekNaam = wegdekNaam;
    }

    public int getSensor() {
        return sensor;
    }

    public void setSensor(int sensor) {
        this.sensor = sensor;
    }

    public NodeCar getCars() {
        return cars;
    }

    public void setCars(NodeCar cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Wegdek{" +
                "wegdekNaam='" + wegdekNaam + '\'' +
                ", sensor=" + sensor +
                ", cars=" + cars +
                '}';
    }
}
