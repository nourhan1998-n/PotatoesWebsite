package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userinterests")
public class Userinterest {
    @EmbeddedId
    private UserinterestId id;

    @MapsId("interestsId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interestsId", nullable = false)
    private Interest interests;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public UserinterestId getId() {
        return id;
    }

    public void setId(UserinterestId id) {
        this.id = id;
    }

    public Interest getInterests() {
        return interests;
    }

    public void setInterests(Interest interests) {
        this.interests = interests;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}