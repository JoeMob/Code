package cn.joemob.crawler;

import cn.joemob.data.CommentSummary;
import cn.joemob.data.Phone;
import cn.joemob.data.Price;

import java.util.List;

import org.apache.log4j.Logger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import lombok.Data;

@Data
public class JDPhones implements PageProcessor {

    private int contextPage = 1;

    private static final Logger log = Logger.getLogger(JDPhones.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(2048).setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Safari/605.1.15");

    @Override
    public void process(Page page) {
        String url = page.getRequest().getUrl();
        if (url.startsWith("https://list.jd.com")) {
            if (contextPage > 200) {
                log.warn("Max page = 200.");
            } else {
                log.info("Processing list page [" + url + "]");
                contextPage++;
                StringBuilder nextUrl = new StringBuilder(url);
                nextUrl.delete(url.length() - String.valueOf(contextPage - 1).length(), url.length());
                nextUrl.append(contextPage);
                url = nextUrl.toString();
                List<String> items = page.getHtml().xpath("//div[@class='gl-i-wrap']//div[@class='p-img']/a/@href").all();
                log.info("Get " + items.size() + " items.");
//                for (String item : items) {
//                    page.addTargetRequest(item);
//                }
                page.addTargetRequest(items.get(0));
                page.addTargetRequest(url);
                page.setSkip(true);
            }
        } else if (url.startsWith("https://item.jd.com") || url.startsWith("https://ccc-x.jd.com")) {
            log.info("Processing item information page [" + url + "]");
            Phone phone = new Phone();
            phone.setUrl(url);
            //从URL中提取商品的SKU
            String sku = url.replace("https://item.jd.com/", "").replace(".html", "");
            phone.setId(sku);
            String phoneName = page.getHtml().xpath("//div[@class='sku-name']/text()").get();
            phone.setName(phoneName);
            List<String> phoneDescribes = page.getHtml().xpath("//div[@class='p-parameter']/ul[starts-with(@class,'parameter2')]/li/text()").all();
            phone.setDescribes(phoneDescribes);
            String price = page.getHtml().xpath("//span[@class='p-price']/span[@class='price]/text()").get();
            if (price == null || "".equalsIgnoreCase(price)) {
                log.warn("No price in information page.");
                page.addTargetRequest(String.format("https://p.3.cn/prices/mgets?skuIds=J_%s", sku));
            } else {
                log.error("Price in information page.");
                phone.setPrice(price);
            }
            page.addTargetRequest("https://club.jd.com/comment/productCommentSummaries.action?referenceIds=" + sku);
            log.info("Phone: " + phone);
            page.putField("PHONE_INFO", phone);
        } else if (url.startsWith("https://p.3.cn/prices/mgets")) {
            log.info("Processing price json [" + url + "]...");
            Price price = new Price();
            String id = page.getJson().jsonPath("$[0].id").get();
            id = id.substring(2);
            price.setId(id);
            String itemPrice = page.getJson().jsonPath("$[0].p").get();
            price.setPrice(itemPrice);
            if (price.getPrice().equals("")) {
                log.error("无法解析价格信息:URL=" + url + ", 消息内容=" + page.getJson());
                page.setSkip(true);
            } else {
                log.info("Price: " + price);
                page.putField("PRICE_INFO", price);
            }
        } else if (url.startsWith("https://club.jd.com/")) {
            log.info("Processing comment json [" + url + "]");
            CommentSummary commentSummary = new CommentSummary();
            String id = page.getJson().jsonPath("$.CommentsCount[0].SkuId").get();
            commentSummary.setId(id);
            String rating = page.getJson().jsonPath("$.CommentsCount[0].GoodRate").get();
            commentSummary.setRating(rating);
            String commentCount = page.getJson().jsonPath("$.CommentsCount[0].CommentCount").get();
            commentSummary.setCommentCount(commentCount);
            log.info("Comment summary: " + commentSummary);
            page.putField("COMMENT_INFO", commentSummary);
        }
    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
