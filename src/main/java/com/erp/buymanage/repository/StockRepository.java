package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>, QuerydslPredicateExecutor<Stock> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE tbl_stock set sin = sin + :sin, stock = stock + :sin where scode = :scode", nativeQuery = true)
    public void updateSin(@Param("sin") int sin, @Param("scode") String scode);

    public Stock findByScode(String scode);

}
