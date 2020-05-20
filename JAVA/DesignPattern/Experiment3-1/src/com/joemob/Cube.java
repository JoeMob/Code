package com.joemob;

public class Cube {
    private Double L;

    public Cube(Double l) {
        L = l;
    }

    public Double getL() {
        return L;
    }

    public double calculateVolume() {
        return L * L * L;
    }

    public double calculateFaceArea() {
        return 6 * L * L;
    }
}
