package com.jfarro.app.services.impl;

import com.jfarro.app.models.domains.Sale;
import com.jfarro.app.models.domains.SaleDetail;
import com.jfarro.app.repositories.SaleDetailRepository;
import com.jfarro.app.repositories.SaleRepository;
import com.jfarro.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sale> findByIdSale(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDetail> findAllSaleDetails() {
        return saleDetailRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SaleDetail> findByIdSaleDetail(Long id) {
        return saleDetailRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveSaleDetail(SaleDetail saleDetail) {
        saleDetailRepository.save(saleDetail);
    }

    @Override
    @Transactional
    public void deleteSaleDetail(Long id) {
        saleDetailRepository.deleteById(id);
    }
}
