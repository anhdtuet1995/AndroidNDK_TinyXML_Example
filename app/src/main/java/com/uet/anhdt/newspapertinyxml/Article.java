package com.uet.anhdt.newspapertinyxml;

/**
 * Created by anhdt on 4/9/2017.
 */

public class Article {

    private String title;
    private String pubDate;
    private String link;

    public Article(String title, String link, String pubDate) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
