package com.joemob;

public class IdolStar implements Star {

    private Manager manager;

    IdolStar(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void performance() {
        System.out.println("Idol star can't performance");
    }

    @Override
    public void communicate() {
        System.out.println("Star answered.");
    }

    @Override
    public void activity() {
        System.out.println("Star attendance.");
    }
}
