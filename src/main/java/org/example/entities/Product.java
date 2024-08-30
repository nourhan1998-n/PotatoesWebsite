package org.example.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "idproduct", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "quantity", precision = 10)
    private BigDecimal quantity;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "img", length = 45)
    private String img;

    @Column(name = "size", length = 45)
    private String size;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcategory", nullable = false)
    private Category idcategory;

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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

}