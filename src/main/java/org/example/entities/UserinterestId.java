package org.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class UserinterestId implements java.io.Serializable {
    private static final long serialVersionUID = 6118108806673396192L;
    @Column(name = "iduser", nullable = false)
    private Integer iduser;

    @Column(name = "idinterests", nullable = false)
    private Integer idinterests;

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIdinterests() {
        return idinterests;
    }

    public void setIdinterests(Integer idinterests) {
        this.idinterests = idinterests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserinterestId entity = (UserinterestId) o;
        return Objects.equals(this.iduser, entity.iduser) &&
                Objects.equals(this.idinterests, entity.idinterests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iduser, idinterests);
    }

}