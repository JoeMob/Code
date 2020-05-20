package com.joemob;

public class MagicFinger implements BallInterface {
    private double Radius;
    private Cube Adaptee;

    MagicFinger(Cube adaptee) {
        Adaptee = adaptee;
        Radius = adaptee.getL();
    }

    @Override
    public double calculateArea() {
        return Radius * Radius * 3.14 * 4;
    }

    @Override
    public double calculateVolume() {
        return Radius * Radius * Radius * 3.14 * 4 / 3;
    }
}
