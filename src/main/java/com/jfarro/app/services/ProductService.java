package com.jfarro.app.services;

import com.jfarro.app.models.domains.Mark;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.models.domains.ProductCategory;
import com.jfarro.app.models.domains.ProductSubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //Product
    Page<Product> findAllPagesProducts(Pageable pageable);
    List<Product> findAllProducts();
    Optional<Product> findByIdProduct(Long id);
    void saveProduct(Product product);
    void updateStateProduct(Byte state, Long id);

    //ProductSubCategory
    Page<ProductSubCategory> findAllPagesProductSubCategories(Pageable pageable);
    List<ProductSubCategory> findAllProductSubCategories();
    Optional<ProductSubCategory> findByIdProductSubCategory(Long id);
    void saveProductSubCategory(ProductSubCategory productSubCategory);
    void updateStateProductSubCategory(Byte state, Long id);

    //ProductCategory
    Page<ProductCategory> findAllPagesProductCategories(Pageable pageable);
    List<ProductCategory> findAllProductCategories();
    Optional<ProductCategory> findByIdProductCategory(Long id);
    void saveProductCategory(ProductCategory productCategory);
    void updateStateProductCategory(Byte state, Long id);

    //Mark
    Page<Mark> findAllPagesMarks(Pageable pageable);
    List<Mark> findAllMarks();
    Optional<Mark> findByIdMarks(Long id);
    void saveMark(Mark mark);
    void updateStateMark(Byte state, Long id);
}
