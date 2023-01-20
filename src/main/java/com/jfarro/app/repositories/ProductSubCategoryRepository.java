package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {

    @Modifying
    @Query("UPDATE ProductSubCategory s SET s.userHistory.state = :state WHERE s.id = :id")
    void updateState(Byte state, Long id);
}
