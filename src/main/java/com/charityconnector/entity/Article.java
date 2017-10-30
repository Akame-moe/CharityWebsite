package com.charityconnector.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "article")
public class Article {
    private Long id;
    private String title;
    private String body;
    private Long charityId;
    private Date insert_time;
    private Date update_time;


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



    @Column(name="insert_time")
    public Date getInsertTime() {
        return insert_time;
    }

    public void setInsertTime(Date insert_time) {
        this.insert_time = insert_time;
    }

    @Column(name="update_time")
    public Date getUpdateTime() {
        return update_time;
    }

    public void setUpdateTime(Date update_time) {
        this.update_time = update_time;
    }


}


