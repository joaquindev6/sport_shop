package com.jfarro.app.repositories;

import com.jfarro.app.models.domains.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}
