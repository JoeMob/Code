package cn.joemob;

import cn.joemob.index.Indexer;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Index {

    private static final Logger log = Logger.getLogger(Index.class);

    public static void main(String[] args) throws ParseException, SQLException, IOException {
        log.info("Starting Index");
        String dir = "D:/Code/JAVA/InformationSearching&SearchEngine/Experiment2/Index";
        Indexer indexer = new Indexer(dir);
        indexer.buildIndex();
    }
}
