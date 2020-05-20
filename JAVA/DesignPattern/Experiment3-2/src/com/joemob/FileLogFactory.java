package com.joemob;

public class FileLogFactory implements LogFactory {
    @Override
    public Log createLog() {
        return new FileLog();
    }
}
