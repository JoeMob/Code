package cn.joemob;

import cn.joemob.search.Searcher;
import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

public class Search {

    private static final Logger log = Logger.getLogger(Search.class);

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
        log.info("Starting search");
        String dir = "D:/Code/JAVA/InformationSearching&SearchEngine/Experiment2/Index";
        Searcher searcher = new Searcher(dir);
        searcher.getDocCounts("ID");
        System.out.println("<------------------------------------------------------------------------------------------------------------------------------>");
        searcher.searchByName("Apple");
        System.out.println("<------------------------------------------------------------------------------------------------------------------------------>");
        searcher.searchPriceBetween(1000, 3500);
        System.out.println("<------------------------------------------------------------------------------------------------------------------------------>");
        searcher.booleanQuery("Apple", "摄像头");
        System.out.println("<------------------------------------------------------------------------------------------------------------------------------>");
        searcher.queryParser("Apple", "摄像头");
    }
}
