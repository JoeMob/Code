package cn.joemob.index;

import cn.joemob.Utli.JDBC;
import org.apache.log4j.Logger;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.hankcs.lucene.HanLPAnalyzer;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Indexer {

    private static final Logger log = Logger.getLogger(Indexer.class);

    private IndexWriter writer;

    private static JDBC jdbc = new JDBC();

    /**
     * 构造函数，负责初始化IndexWriter
     *
     * @param dir 索引目录
     */
    public Indexer(String dir) {
        HanLPAnalyzer analyzer = new HanLPAnalyzer();
        Directory index;
        try {
            index = FSDirectory.open(Paths.get(dir));
            IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(index, writerConfig);
            log.info("索引初始化完成，索引目录为:" + dir);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("无法初始化索引，请检查提供的索引目录是否可用:" + dir);
            writer = null;
        }
    }

    public void buildIndex() throws IOException, ParseException, SQLException {
        int count = 0;    //计数器
        // 多表连接查询
        String sql = "select ci.id, ci.name,ci.describes,ci.url,cp.price,cc.comment_count,cc.rating from cellphoneinfo ci join cellphoneprice cp on ci.id = cp.id join cellphonecomment cc on ci.id=cc.id";
        ResultSet cellphone = jdbc.select(sql);

        while (cellphone.next()) {
            String id = cellphone.getString("id");
            String name = cellphone.getString("name");
            String describes = cellphone.getString("describes");
            String url = cellphone.getString("url");
            double price = cellphone.getDouble("price");
            int commentCount = cellphone.getInt("comment_count");
            double rating = cellphone.getDouble("rating");

            Document doc = new Document();
            // Field的子类用法：查官方文档
            doc.add(new StringField("ID", id, Field.Store.YES));
            doc.add(new TextField("NAME", name, Field.Store.YES));
            doc.add(new TextField("DESCRIBES", describes, Field.Store.YES));
            doc.add(new StoredField("URL", url));
            doc.add(new DoublePoint("PRICE", price));
            doc.add(new IntPoint("COMMENT_COUNT", commentCount));
            doc.add(new DoublePoint("RATING", rating));
            writer.updateDocument(new Term("ID", id), doc);
            writer.commit();
            count++;
            log.info("第" + count + "部手机【" + id + "】创建索引成功");
        }
        writer.close();
        jdbc.close();
    }

    public void destroy() {
        if (writer == null)
            return;
        try {
            log.info("索引关闭");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("尝试关闭索引失败");
        }
    }
}
