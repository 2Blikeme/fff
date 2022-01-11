package com.mysite.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "playstation_preorders")
public class playstation_preorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "ps_id")
    private int psId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPsId() {
        return psId;
    }

    public void setPsId(int psId) {
        this.psId = psId;
    }
}
