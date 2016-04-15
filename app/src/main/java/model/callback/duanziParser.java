package model.callback;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import model.bean.duanzi;
import view.fragment.DuanziFragment;

//解析pic html 的类
public class DuanziParser {
    ArrayList results = new ArrayList<>();

    public DuanziParser(String html) {
        try {
            Document doc = Jsoup.parse(html);
            Element body = doc.body();
            Elements items = body.select("ol.commentlist").select("li");
            for (Element item : items) {
                if (DuanziFragment.mPage == 0)
                    DuanziFragment.mPage = getPage(item);
                String textattuibitt = item.text();
                duanzi g = new duanzi();
                g.setContent(getContent(item));
                if ("".equals(g.getContent()))
                    continue;
                g.setAuthor(getAuthor(textattuibitt));
                g.setPostTime(getposttime(textattuibitt));
                g.setLike(getlike(textattuibitt));
                g.setDislike(dislike(textattuibitt));
                Log.i("duanziTAG", g.toString());
                results.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getResult() {
        return results;
    }

    public static String getAuthor(String str) throws Exception {
        return str.split("\\ ")[0];
    }

    public static String getposttime(String str) throws Exception {
        String posttime = "";
        try {
            posttime = str.split("\\ ")[2] + str.split("\\ ")[3];
        } catch (Exception e) {
            posttime = "";
        }
        return posttime;
    }


    public static String getContent(Element ele) throws Exception {
        String content = "";
        try {
            content = ele.select("p").first().text();
        } catch (Exception e) {
            content = "";
        }

        return content;
    }

    public static String getlike(String str) throws Exception {
        String like = "";
        try {
            like = str.split("\\ OO")[1].split("\\ XX")[0];
        } catch (Exception e) {
            like = "";
        }
        return like;
    }

    public static String dislike(String str) throws Exception {
        String like = "";
        try {
            like = str.split("\\ OO")[1].split("\\ XX")[1];
        } catch (Exception e) {
            like = "";
        }
        return like;
    }

    private int getPage(Element item) throws Exception {
        String aherf = (item.select("a[href]").get(1)).toString();
        String pageNum = aherf.split("\\-")[1].split("\\#")[0];
        Log.i("pageNumTAG", pageNum);
        return Integer.valueOf(pageNum);
    }
}
