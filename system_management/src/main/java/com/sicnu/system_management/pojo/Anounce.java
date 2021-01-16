package com.sicnu.system_management.pojo;

/**
 * 公告
 */
public class Anounce {
    private Integer anounce_id;
    private String anounce_title;
    private String anounce_date;
    private Integer anounce_author;
    private String anounce_text;

    public Integer getAnounce_id() {
        return anounce_id;
    }

    public void setAnounce_id(Integer anounce_id) {
        this.anounce_id = anounce_id;
    }

    public String getAnounce_title() {
        return anounce_title;
    }

    public void setAnounce_title(String anounce_title) {
        this.anounce_title = anounce_title;
    }

    public String getAnounce_date() {
        return anounce_date;
    }

    public void setAnounce_date(String anounce_date) {
        this.anounce_date = anounce_date;
    }

    public Integer getAnounce_author() {
        return anounce_author;
    }

    public void setAnounce_author(Integer anounce_author) {
        this.anounce_author = anounce_author;
    }

    public String getAnounce_text() {
        return anounce_text;
    }

    public void setAnounce_text(String anounce_text) {
        this.anounce_text = anounce_text;
    }

    @Override
    public String toString() {
        return "Anounce{" +
                "anounce_id=" + anounce_id +
                ", anounce_title='" + anounce_title + '\'' +
                ", anounce_date='" + anounce_date + '\'' +
                ", anounce_author=" + anounce_author +
                ", anounce_text='" + anounce_text + '\'' +
                '}';
    }
}
