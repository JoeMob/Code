package cn.joemob.search;

import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoublePoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;

public class Searcher {

    private static final Logger log = Logger.getLogger(Searcher.class);

    private IndexSearcher searcher;

    private DirectoryReader reader;


    public Searcher(String dir) {
        try {
            this.reader = DirectoryReader.open(FSDirectory.open(Paths.get(dir)));
            this.searcher = new IndexSearcher(reader);
            log.info("IndexSearcher初始化成功");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("IndexSearcher初始化失败");
        }
    }

    public int getDocCounts(String field) {
        if (reader != null) {
            try {
                return reader.getDocCount(field);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("读取索引过程中出现异常");
                return -1;
            }
        }
        return -1;
    }

    public void searchByName(String parOfName) throws IOException, ParseException {
        TermQuery query = new TermQuery(new Term("NAME", parOfName));
        int hitCounts = 100;
        TopDocs results = searcher.search(query, hitCounts);
        ScoreDoc[] hits = results.scoreDocs;
        long hitsNum = results.totalHits.value;
        log.info("There's/'re " + hitsNum + " phone's/(s') name include " + parOfName);
        for (int i = 0; i < hitsNum; i++) {
            Document doc = searcher.doc(hits[i].doc);
            String name = doc.get("NAME");
            String url = doc.get("URL");
            log.info(url + "/" + name);
        }
    }

    public void searchPriceBetween(double min, double max) throws IOException {
        Query query = DoublePoint.newRangeQuery("PRICE", min, max);
        int hitsCount = 100;
        TopDocs results = searcher.search(query, hitsCount);
        ScoreDoc[] hits = results.scoreDocs;
        long hitsNum = results.totalHits.value;
        log.info("There's/'re " + hitsNum + " phone's/(s') price between " + min + " and " + max);
        for (int i = 0; i < hitsNum; i++) {
            Document doc = searcher.doc(hits[i].doc);
            String name = doc.get("NAME");
            String url = doc.get("URL");
            log.info(url + "/" + name);
        }
    }

    public void booleanQuery(String partOfName, String partOfDescribes) throws IOException {
        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
        TermQuery nameQuery = new TermQuery(new Term("NAME", partOfName));
        TermQuery describesQuery = new TermQuery(new Term("DESCRIBES", partOfDescribes));
        queryBuilder.add(nameQuery, BooleanClause.Occur.MUST).add(describesQuery, BooleanClause.Occur.MUST);
        BooleanQuery query = queryBuilder.build();
        int hitsCount = 100;
        TopDocs results = searcher.search(query, hitsCount);
        ScoreDoc[] hits = results.scoreDocs;
        long hitsNum = results.totalHits.value;
        log.info("There's/re " + hitsNum + " phone's/(s') name include " + partOfName + " and describe include " + partOfDescribes);
        for (int i = 0; i < hitsNum; i++) {
            Document doc = searcher.doc(hits[i].doc);
            String name = doc.get("NAME");
            String url = doc.get("URL");
            log.info(url + "/" + name);
        }
    }

    public void queryParser(String partOfName, String partOfDescribes) throws org.apache.lucene.queryparser.classic.ParseException, IOException {
        QueryParser queryParser = new QueryParser("NAME", new HanLPAnalyzer());
        Query query = queryParser.parse(partOfName + " AND DESCRIBES:" + partOfDescribes);
        System.out.println(query);
        int hitsCount = 100;
        TopDocs results = searcher.search(query, hitsCount);
        ScoreDoc[] hits = results.scoreDocs;
        long hitsNum = results.totalHits.value;
        log.info("There's/re " + hitsNum + " phone's/(s') name include " + partOfName + " and describe include " + partOfDescribes);
        for (int i = 0; i < hitsNum; i++) {
            Document doc = searcher.doc(hits[i].doc);
            String name = doc.get("NAME");
            String url = doc.get("URL");
            log.info(url + "/" + name);
        }
    }

    public void destroy() {
        if (this.reader != null) {
            try {
                this.reader.close();
                log.info("关闭搜索服务");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
