package bean;

public class girl {
    String thumburl;
    String largeurl;
    String author;
    String posttime;
    String like;
    String dislike;

    public girl(String thumburl, String largeurl, String author, String posttime, String like, String dislike) {
        this.thumburl = thumburl;
        this.largeurl = largeurl;
        this.author = author;
        this.posttime = posttime;
        this.like = like;
        this.dislike = dislike;
    }

    public girl() {
    }

    public String getThumburl() {
        return thumburl;
    }

    public String getLargeurl() {
        return largeurl;
    }

    public String getAuthor() {
        return author;
    }

    public String getPosttime() {
        return posttime;
    }

    public String getLike() {
        return like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public void setLargeurl(String largeurl) {
        this.largeurl = largeurl;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    @Override
    public String toString() {
        return "girl{" +
                "thumburl='" + thumburl + '\'' +
                ", largeurl='" + largeurl + '\'' +
                ", author='" + author + '\'' +
                ", posttime='" + posttime + '\'' +
                ", like='" + like + '\'' +
                ", dislike='" + dislike + '\'' +
                '}';
    }
}
