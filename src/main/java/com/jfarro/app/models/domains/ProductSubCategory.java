package com.jfarro.app.models.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_sub_categorias")
public class ProductSubCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank
    private String name;

    @Column(name = "descripcion")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private ProductCategory category;

    @Embedded
    @NotNull
    private UserHistory userHistory;

    public ProductSubCategory() {
        this.userHistory = new UserHistory();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public UserHistory getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }
}
