package com.joemob;

public class DBLogFactory implements LogFactory {
    @Override
    public Log createLog() {
        return new DBLog();
    }
}
