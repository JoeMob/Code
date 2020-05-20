package com.joemob;

public class FileLog implements Log {
    @Override
    public void writeLog() {
        System.out.println("Writing file log.");
    }
}
