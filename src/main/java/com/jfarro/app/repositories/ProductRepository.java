package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.userHistory.state = :state WHERE p.id = :id")
    void updateState(@Param("state") Byte state, @Param("id") Long id);

    List<Product> findAllBySubCategoryName(String name);
}
