package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Modifying
    @Query("UPDATE ProductCategory c SET c.userHistory.state = :state WHERE c.id = :id")
    void updateState(@Param("state") Byte state, @Param("id") Long id);
}
