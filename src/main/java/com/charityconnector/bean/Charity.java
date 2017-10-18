package com.charityconnector.bean;

import javax.persistence.*;

@Entity
@Table(name = "charity")
public class Charity {


    private Long id;
    private String name;
    private String description;
    private String logoPath;
    /* Required by JPA specification */
    public Charity() {
        super();
    }

    public Charity(String desciption,Long id){
        this.description = desciption;
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

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="logo_path")
    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}


