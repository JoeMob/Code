package com.joemob;

public class Main {

    public static void main(String[] args) {
        Cube cube = new Cube(2.0);
        BallInterface ball = new MagicFinger(cube);
        System.out.println("Cube's exterior area: " + cube.calculateFaceArea());
        System.out.println("Cube's volume: " + cube.calculateVolume());
        System.out.println("Ball's exterior area: " + ball.calculateArea());
        System.out.println("Ball's volume: " + ball.calculateVolume());
    }
}
