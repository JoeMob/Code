package cn.joemob.crawler;

import cn.joemob.Utli.JDBC;
import cn.joemob.data.CommentSummary;
import cn.joemob.data.Phone;
import cn.joemob.data.Price;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class LucenePipeline implements Pipeline {
    protected static JDBC jdbc = new JDBC();

    public void process(ResultItems resultItems, Task task) {
        if (resultItems.get("PHONE_INFO") != null) {
            Phone phone = resultItems.get("PHONE_INFO");
            String id = phone.getId();
            String name = phone.getName();
            String describes = String.join(",", phone.getDescribes());
            String url = phone.getUrl();
            String sql = "INSERT INTO cellphoneinfo VALUES('" + id + "','" + name + "','" + describes + "','" + url + "') ON DUPLICATE KEY UPDATE name = '" + name + "',describes = '" + describes + "',url = '" + url + "';";
            jdbc.insert(sql);
        }
        if (resultItems.get("PRICE_INFO") != null) {
            Price price = resultItems.get("PRICE_INFO");
            String id = price.getId();
            String phonePrice = price.getPrice();
            String sql = "INSERT INTO cellphoneprice (id,price) VALUES('" + id + "','" + phonePrice + "') ON DUPLICATE KEY UPDATE price = '" + phonePrice + "';";
            jdbc.insert(sql);
        }
        if (resultItems.get("COMMENT_INFO") != null) {
            CommentSummary commentSummary = resultItems.get("COMMENT_INFO");
            String id = commentSummary.getId();
            String commentCount = commentSummary.getCommentCount();
            String rating = commentSummary.getRating();
            String sql = "INSERT INTO cellphonecomment (id,comment_count,rating) VALUES('" + id + "','" + commentCount + "','" + rating + "') ON DUPLICATE KEY UPDATE comment_count = '" + commentCount + "',rating = '" + rating + "';";
            jdbc.insert(sql);
        }
    }
}
