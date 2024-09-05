package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin", schema = "vegesfood", uniqueConstraints = {
        @UniqueConstraint(name = "email_UNIQUE", columnNames = {"email"})
})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadmin", nullable = false)
    private Integer id;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}