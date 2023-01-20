package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Modifying
    @Query("UPDATE Role r SET r.userHistory.state = :state WHERE r.id = :id")
    void updateState(Byte state, Long id);
}
