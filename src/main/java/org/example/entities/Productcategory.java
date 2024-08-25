package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "productcategory")
public class Productcategory {
    @EmbeddedId
    private ProductcategoryId id;

    @MapsId("idproduct")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idproduct", nullable = false)
    private Product idproduct;

    @MapsId("idcategory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idcategory", nullable = false)
    private Category idcategory;

    public ProductcategoryId getId() {
        return id;
    }

    public void setId(ProductcategoryId id) {
        this.id = id;
    }

    public Product getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(Product idproduct) {
        this.idproduct = idproduct;
    }

    public Category getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(Category idcategory) {
        this.idcategory = idcategory;
    }

}