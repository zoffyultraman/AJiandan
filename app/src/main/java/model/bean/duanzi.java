package model.bean;

import java.io.Serializable;

//吐槽。。 暫時不添加
public class duanzi {

    String Author;
    String Content;
    String Like;
    String Dislike;
    String PostTime;

    public duanzi(String author,  String content,String like, String dislike,String Posttime) {
        Author = author;
        Content = content;
        Like = like;
        Dislike = dislike;
        PostTime = Posttime;
    }

    public duanzi() {
    }

    public String getPostTime() {
        return PostTime;
    }

    public void setPostTime(String postTime) {
        PostTime = postTime;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setContent(String content) {
        Content = content;
    }


    public void setLike(String like) {
        Like = like;
    }

    public void setDislike(String dislike) {
        Dislike = dislike;
    }

    public String getAuthor() {
        return Author;
    }


    public String getContent() {
        return Content;
    }


    public String getLike() {
        return Like;
    }

    public String getDislike() {
        return Dislike;
    }

    @Override
    public String toString() {
        return "duanzi{" +
                "Author='" + Author + '\'' +
                ", Content='" + Content + '\'' +
                ", Like='" + Like + '\'' +
                ", Dislike='" + Dislike + '\'' +
                ", PostTime='" + PostTime + '\'' +
                '}';
    }
}
