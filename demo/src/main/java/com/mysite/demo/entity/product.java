package com.mysite.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "productdescription")
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "image_path")
    private String image_path;
    @Column(name = "description")
    private String description;
    @Column(name = "brief_description")
    private String brief_description;
    @Column(name = "link")
    private String link;
    @Column(name = "price")
    private double price;

    public String getLink() {
        return link;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBrief_description() {
        return brief_description;
    }

    public void setBrief_description(String brief_description) {
        this.brief_description = brief_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
