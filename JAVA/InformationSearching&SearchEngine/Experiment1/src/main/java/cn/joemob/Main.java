package cn.joemob;

import cn.joemob.pps.JDPhones;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;

public class Main {

    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting JD phone spider.");
        String startPage = "https://list.jd.com/list.html?cat=9987,653,655&page=1";
        JDPhones crawler = new JDPhones();
        Spider spider = Spider.create(crawler);
        spider.addPipeline(new ConsolePipeline());
        spider.thread(1);
        spider.addUrl(startPage);
        spider.run();
    }
}
