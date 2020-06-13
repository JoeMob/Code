package cn.joemob;

import cn.joemob.crawler.JDPhones;
import cn.joemob.crawler.LucenePipeline;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;
import org.apache.lucene.queryparser.classic.ParseException;
import java.io.IOException;

public class Crawl {

    private static final Logger log = Logger.getLogger(Crawl.class);

    public static void main(String[] args) throws IOException, ParseException {
        log.info("Starting JD phone spider.");
        String startPage = "https://list.jd.com/list.html?cat=9987,653,655&page=1";
        JDPhones crawler = new JDPhones();
        Spider spider = Spider.create(crawler);
        spider.addPipeline(new LucenePipeline());
        spider.thread(1);
        spider.addUrl(startPage);
        spider.run();
    }
}
