package com.javastudy.oop;

public class Car {

    private Engine engine;
    private Battery battery;

    public Car() {
    }

    public Car(Engine engine, Battery battery) {
        this.engine = engine;
        this.battery = battery;
    }

    public void changeBattery() {
        if (battery.lifeEnd()) {
            this.battery = new Battery();
        }
    }

    private void boostEngine() {
        engine.start();
    }

    private void batteryOn() {
        battery.on();
    }

    public void start() {
        batteryOn();
        boostEngine();
    }

    static class Engine {
        public void start() {

        }
    }

    static class Battery {
        public void on() {

        }

        public boolean lifeEnd() {
            return true;
        }
    }
}
