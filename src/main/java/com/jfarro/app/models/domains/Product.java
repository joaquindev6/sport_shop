package com.jfarro.app.models.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_productos")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank
    private String name;

    @Column(name = "cantidad")
    @NotNull
    @Min(0)
    @Max(500)
    private Integer amount;

    @Column(name = "precio")
    @NotNull
    @Min(0)
    private Double price;

    @Column(name = "descripcion")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER) //Lo converti a eager porque al obtener el producto y como era una carga peresoza no me obtenia los datos
    @JoinColumn(name = "id_sub_categoria")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private ProductSubCategory subCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_marca")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private Mark mark;

    @Column(name = "foto")
    private String photo;

    @Embedded
    @NotNull
    private UserHistory userHistory;

    public Product() {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(ProductSubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public UserHistory getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(UserHistory userHistory) {
        this.userHistory = userHistory;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", subCategory=" + subCategory +
                ", mark=" + mark +
                ", photo='" + photo + '\'' +
                ", userHistory=" + userHistory +
                '}';
    }
}
