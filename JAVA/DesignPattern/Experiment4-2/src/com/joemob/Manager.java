package com.joemob;

public class Manager implements Star {
    private Star star;
    private Stand stand;

    public void setStar(Star star) {
        this.star = star;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    @Override
    public void performance() {
        star.performance();
        callStand();
    }

    @Override
    public void communicate() {
        System.out.println("Communicate with star through Manager.");
        star.communicate();
    }

    @Override
    public void activity() {
        System.out.println("Start activity for star.");
        star.activity();
    }

    public void callStand() {
        stand.performance();
    }
}
