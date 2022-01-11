package com.mysite.demo.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
public class cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userId")
    private User user;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "cart_games",
                joinColumns = @JoinColumn(name = "cart_id"),
                inverseJoinColumns = @JoinColumn(name = "gameId"))
    List<game> GamesPreorders;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "cart_playstation",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "psId"))
    List<playstation> PlaystationPreorders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<game> getGamesPreorders() {
        return GamesPreorders;
    }

    public void setGamesPreorders(List<game> gamesPreorders) {
        GamesPreorders = gamesPreorders;
    }

    public List<playstation> getPlaystationPreorders() {
        return PlaystationPreorders;
    }

    public void setPlaystationPreorders(List<playstation> playstationPreorders) {
        PlaystationPreorders = playstationPreorders;
    }

}
