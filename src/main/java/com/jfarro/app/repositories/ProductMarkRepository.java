package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMarkRepository extends JpaRepository<Mark, Long> {

    @Modifying
    @Query("UPDATE Mark m SET m.userHistory.state = :state WHERE m.id = :id")
    void updateState(@Param("state") Byte state, @Param("id") Long id);
}
