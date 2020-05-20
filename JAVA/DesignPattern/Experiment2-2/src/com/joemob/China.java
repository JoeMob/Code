package com.joemob;

public class China extends Country {

    @Override
    public void setUN(UN un) {
        UnitedNations = un;
    }

    @Override
    public void declare(String declaration, Country country) {
        UnitedNations.declare(declaration, country);
    }

    @Override
    public void getMessage(String message) {
        System.out.println("com.joemob.China has got message: " + message);
    }
}
