package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class UserinterestId implements java.io.Serializable {
    private static final long serialVersionUID = -4237039276951662397L;
    @Column(name = "interestsId", nullable = false)
    private Integer interestsId;

    @Column(name = "userId", nullable = false)
    private Integer userId;

    public Integer getInterestsId() {
        return interestsId;
    }

    public void setInterestsId(Integer interestsId) {
        this.interestsId = interestsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserinterestId entity = (UserinterestId) o;
        return Objects.equals(this.interestsId, entity.interestsId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interestsId, userId);
    }

}