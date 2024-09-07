package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "category", schema = "vegesfood")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategory", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)  // Store the enum as a string in the database
    @Column(name = "name", length = 45, nullable = false)
    private CategoryEnum name;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }
}
