package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "category", schema = "vegesfood")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

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

}