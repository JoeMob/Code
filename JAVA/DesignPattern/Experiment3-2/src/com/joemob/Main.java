package com.joemob;

public class Main {

    public static void main(String[] args) {
        // write your code here
        LogFactory logFactory = new FileLogFactory();
        Log log = logFactory.createLog();
        log.writeLog();
        logFactory = new DBLogFactory();
        log = logFactory.createLog();
        log.writeLog();
    }
}
