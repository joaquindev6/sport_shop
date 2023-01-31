package com.jfarro.app.services.impl;

import com.jfarro.app.models.domains.Mark;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.models.domains.ProductCategory;
import com.jfarro.app.models.domains.ProductSubCategory;
import com.jfarro.app.repositories.ProductCategoryRepository;
import com.jfarro.app.repositories.ProductMarkRepository;
import com.jfarro.app.repositories.ProductRepository;
import com.jfarro.app.repositories.ProductSubCategoryRepository;
import com.jfarro.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Autowired
    private ProductMarkRepository markRepository;

    //------------------------ Product ------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAllPagesProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findByIdProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateStateProduct(Byte state, Long id) {
        productRepository.updateState(state, id);
    }

    //------------------------ ProductSubCategory ------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<ProductSubCategory> findAllPagesProductSubCategories(Pageable pageable) {
        return subCategoryRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSubCategory> findAllProductSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductSubCategory> findByIdProductSubCategory(Long id) {
        return subCategoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveProductSubCategory(ProductSubCategory productSubCategory) {
        subCategoryRepository.save(productSubCategory);
    }

    @Override
    @Transactional
    public void updateStateProductSubCategory(Byte state, Long id) {
        subCategoryRepository.updateState(state, id);
    }

    @Override
    public List<Product> findAllBySubCategoryName(String name) {
        return productRepository.findAllBySubCategoryName(name);
    }

    //------------------------ ProductCategory ------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<ProductCategory> findAllPagesProductCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductCategory> findAllProductCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductCategory> findByIdProductCategory(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveProductCategory(ProductCategory productCategory) {
        categoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void updateStateProductCategory(Byte state, Long id) {
        categoryRepository.updateState(state, id);
    }

    //------------------------ Mark ------------------------
    @Override
    @Transactional(readOnly = true)
    public Page<Mark> findAllPagesMarks(Pageable pageable) {
        return markRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mark> findAllMarks() {
        return markRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mark> findByIdMarks(Long id) {
        return markRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveMark(Mark mark) {
        markRepository.save(mark);
    }

    @Override
    @Transactional
    public void updateStateMark(Byte state, Long id) {
        markRepository.updateState(state, id);
    }
}
