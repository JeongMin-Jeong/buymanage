package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchProductRepository {
    Product search1();
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
