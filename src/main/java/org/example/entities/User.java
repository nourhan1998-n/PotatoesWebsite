package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "iduser", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "job", length = 45)
    private String job;

    @Column(name = "credit", length = 45)
    private String credit;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "street", length = 45)
    private String street;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcart", nullable = false)
    private Cart idcart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idwishlist", nullable = false)
    private Wishlist idwishlist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Cart getIdcart() {
        return idcart;
    }

    public void setIdcart(Cart idcart) {
        this.idcart = idcart;
    }

    public Wishlist getIdwishlist() {
        return idwishlist;
    }

    public void setIdwishlist(Wishlist idwishlist) {
        this.idwishlist = idwishlist;
    }

}