package com.mysite.demo.entity;

import com.mysite.demo.interfaces.Preorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class game implements Preorder {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private int description;

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

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    @Override
    public String getCategory() {
        return "game";
    }
}
