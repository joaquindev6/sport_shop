package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User u SET u.userHistory.state = :state WHERE u.id = :id")
    void updateState(@Param("state") Byte state, @Param("id") Long id);
}
