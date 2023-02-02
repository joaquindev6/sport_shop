package com.jfarro.app.services;

import com.jfarro.app.models.domains.Sale;
import com.jfarro.app.models.domains.SaleDetail;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Sale> findAllSales();
    Optional<Sale> findByIdSale(Long id);
    void saveSale(Sale sale);
    void deleteSale(Long id);

    List<SaleDetail> findAllSaleDetails();
    Optional<SaleDetail> findByIdSaleDetail(Long id);
    void saveSaleDetail(SaleDetail saleDetail);
    void deleteSaleDetail(Long id);
}
