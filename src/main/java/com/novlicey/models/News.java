package com.novlicey.models;

import javax.persistence.*;

@Entity
@Table(name = "news")
public class News {

    private long id;

    private String title;
    private String text;

    public News(String title, String text) {
        this.title = title;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    @Column(name = "text", nullable = false)
    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
