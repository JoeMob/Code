package com.joemob;

public class DBLog implements Log {
    @Override
    public void writeLog() {
        System.out.println("Writing DB log.");
    }
}
