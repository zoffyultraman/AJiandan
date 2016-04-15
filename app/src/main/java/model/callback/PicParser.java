package model.callback;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import model.bean.girl;

//解析pic html 的类
public class PicParser {

     List results = new ArrayList<>();

    public PicParser(String html) {
        try {
            Document doc = Jsoup.parse(html);
            Element body = doc.body();
            Elements items = body.select("ol.commentlist").select("li");
            for (Element item : items) {
                String textattuibitt = item.text();
                girl g = new girl();
                g.setAuthor(getAuthor(textattuibitt));
                g.setPosttime(getposttime(textattuibitt));
                g.setLike(getlike(textattuibitt));
                g.setDislike(dislike(textattuibitt));
                g.setLargeurl(getLargeImg(item));
                g.setThumburl(g.getLargeurl().replace("large","mw600"));
                results.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  List getResult(){
        return results;
    }

    public static String getAuthor(String str) {
        return str.split("\\ ")[0];
    }

    public static String getposttime(String str) {
        String posttime = "..";
        try {
            posttime = str.split("\\ ")[2] + str.split("\\ ")[3];
        } catch (Exception e) {
            posttime = "..";
        }
        return posttime;
    }

    public static String getlike(String str) {
        String like = "1";
        try {
            like = str.split("\\ OO")[1].split("\\ XX")[0];
        } catch (Exception e) {
            like = "1";
        }
        return like;
    }

    public static String dislike(String str) {
        String like = "1";
        try {
            like = str.split("\\ OO")[1].split("\\ XX")[1];
        } catch (Exception e) {
            like = "1";
        }
        return like;
    }

    public static String getLargeImg(Element html) {
        String LargeImg = "";
        try {
            Element element = html.select("div.commenttext").first();
            String outerHtml = element.select("a").first().outerHtml();
            LargeImg = outerHtml.split("\\\"")[1].split("\\\"")[0];
        } catch (Exception e) {
            return "";
        }
        return LargeImg;
    }
//
//    public static String getThumbImg(Element html) {
//        String Thumburl = "";
//        try {
//            Element element = html.select("div.commenttext").first();
//            String outerHtml = element.select("a").first().outerHtml();
//            Thumburl = outerHtml.split("\\\"")[1].split("\\\"")[0];
//        } catch (Exception e) {
//            return "";
//        }
//        return Thumburl;
//    }
}
