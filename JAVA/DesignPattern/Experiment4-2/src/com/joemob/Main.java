package com.joemob;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Stand stand = new Stand();
        Manager imanager = new Manager();
        Star Idol = new IdolStar(imanager);
        imanager.setStar(Idol);
        imanager.setStand(stand);
        imanager.communicate();
        imanager.activity();
        imanager.performance();
    }
}
