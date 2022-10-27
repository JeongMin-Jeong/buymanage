package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {
    @Query("SELECT p, pi " +
            "FROM Product p " +
            "LEFT OUTER JOIN ProductImage pi ON pi.product = p " +
            "GROUP BY p")
    List<Object[]> getListPage(Pageable pageable);

    @Query("SELECT p, pi " +
            "FROM Product p " +
            "LEFT OUTER JOIN ProductImage pi on pi.product = p " +
            "WHERE p.pno =:pno GROUP BY pi")
    List<Object[]> getProductWithAll(Long pno);
}
