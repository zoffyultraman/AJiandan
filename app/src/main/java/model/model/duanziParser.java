package model.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import model.bean.duanzi;

//解析pic html 的类
public class duanziParser {
    ArrayList results = new ArrayList<>();

    public duanziParser(String html) {
        try {
            Document doc = Jsoup.parse(html);
            Element body = doc.body();
            Elements items = body.select("ol.commentlist").select("li");
            for (Element item : items) {
                String textattuibitt = item.text();
                duanzi g = new duanzi();
                g.setContent(getContent(item));
                if("".equals(g.getContent()))
                    continue;
                g.setAuthor(getAuthor(textattuibitt));
                g.setPostTime(getposttime(textattuibitt));
                g.setLike(getlike(textattuibitt));
                g.setDislike(dislike(textattuibitt));


                results.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getResult() {
        return results;
    }

    public static String getAuthor(String str) {
        return str.split("\\ ")[0];
    }

    public static String getposttime(String str) {
        String posttime = "";
        try {
            posttime = str.split("\\ ")[2] + str.split("\\ ")[3];
        } catch (Exception e) {
            posttime = "";
        }
        return posttime;
    }


    public static String getContent(Element ele) {
        String content = "";
        try {
            content = ele.select("p").first().text();
        } catch (Exception e) {
            content = "";
        }

        return content;
    }

    public static String getlike(String str) {
        String like = "";
        try {
            like = str.split("\\ OO")[1].split("\\ XX")[0];
        } catch (Exception e) {
            like = "";
        }
        return like;
    }

    public static String dislike(String str) {
        String like = "";
        try {
            like = str.split("\\ OO")[1].split("\\ XX")[1];
        } catch (Exception e) {
            like = "";
        }
        return like;
    }
}
