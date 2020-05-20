package com.joemob;

public class Main {
    public static void main(String[] args) {
        China china = new China();
        America america = new America();
        WTO wto = new WTO(china, america);
        china.setUN(wto);
        america.setUN(wto);
        china.declare("Across firewall.", america);
        america.declare("Across mexicanwall", china);
    }
}
