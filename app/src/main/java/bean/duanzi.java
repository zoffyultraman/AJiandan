package bean;

import java.io.Serializable;

//吐槽。。 暫時不添加
public class duanzi {

    String Author;
    String Date;
    String Content;
    String Title;
    String Like;
    String Dislike;

    public duanzi(String author, String date, String content, String title, String like, String dislike) {
        Author = author;
        Date = date;
        Content = content;
        Title = title;
        Like = like;
        Dislike = dislike;
    }

    public duanzi() {
    }


    public void setAuthor(String author) {
        Author = author;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setContent(String content) {
        Content = content;
    }

    public void setTitle(String title) {
        Title = title;
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

    public String getDate() {
        return Date;
    }

    public String getContent() {
        return Content;
    }

    public String getTitle() {
        return Title;
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
                ", Date='" + Date + '\'' +
                ", Content='" + Content + '\'' +
                ", Title='" + Title + '\'' +
                ", Like='" + Like + '\'' +
                ", Dislike='" + Dislike + '\'' +
                '}';
    }
}
