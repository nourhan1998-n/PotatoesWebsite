package org.example.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product", schema = "vegesfood", indexes = {
        @Index(name = "fk_product_category1_idx", columnList = "idcategory")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "quantity", precision = 10)
    private BigDecimal quantity;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "img", length = 200)
    private String img;

    @Column(name = "size", length = 45)
    private String size;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
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






//<c:forEach var="cartitem" items="${cartitems}">
//    <!-- Display Cartitem details -->
//    <p>Quantity: ${cartitem.quantity}</p>
//
//    <!-- Access the corresponding Product using the productMap -->
//    <c:set var="product" value="${productMap[cartitem.id.idproduct]}" />
//
//    <!-- Display Product details -->
//<p>Product Name: ${product.name}</p>
//<p>Product Price: ${product.price}</p>
//<p>Product Image: <img src="${product.imagePath}" alt="${product.name}"/></p>
//</c:forEach>






















