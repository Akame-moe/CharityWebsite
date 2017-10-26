package com.charityconnector.entity;

import javax.persistence.*;


@Entity
@Table(name = "article")
public class Article {
    private Long id;
    private String title;
    private String body;
    private Long charityId;

    /* Required by JPA specification */
    public Article() {
        super();
    }

    public Article(String title, Long id){
        this.title = title;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Column(name="charity_id")
    public long getCharityId() {
        return charityId;
    }

    public void setCharityId(long charityId) {
        this.charityId = charityId;
    }

}

