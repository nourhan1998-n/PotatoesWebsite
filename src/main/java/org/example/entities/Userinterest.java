package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "userinterests", schema = "vegesfood", indexes = {
        @Index(name = "fk_user_has_category_user1_idx", columnList = "iduser"),
        @Index(name = "fk_user_has_category_category1_idx", columnList = "idcategory")
})
public class Userinterest {
    @EmbeddedId
    private UserinterestId id;

    @MapsId("iduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iduser", nullable = false)
    private User iduser;

    @MapsId("idcategory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcategory", nullable = false)
    private Category idcategory;

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

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

}