package com.joemob;

public class America extends Country {

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
        System.out.println("USA has got message: " + message);
    }
}
