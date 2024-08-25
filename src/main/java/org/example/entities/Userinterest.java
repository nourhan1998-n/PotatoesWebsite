package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userinterests")
public class Userinterest {
    @EmbeddedId
    private UserinterestId id;

    @MapsId("iduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User iduser;

    @MapsId("idinterests")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idinterests", nullable = false)
    private Interest idinterests;

    public UserinterestId getId() {
        return id;
    }

    public void setId(UserinterestId id) {
        this.id = id;
    }

    public User getIduser() {
        return iduser;
    }

    public void setIduser(User iduser) {
        this.iduser = iduser;
    }

    public Interest getIdinterests() {
        return idinterests;
    }

    public void setIdinterests(Interest idinterests) {
        this.idinterests = idinterests;
    }

}