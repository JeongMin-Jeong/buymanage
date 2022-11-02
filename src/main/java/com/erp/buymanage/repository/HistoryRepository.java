package com.erp.buymanage.repository;

import com.erp.buymanage.entity.History;
import com.erp.buymanage.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long>, QuerydslPredicateExecutor<History> {

    //Stock으로 내역 목록 가져오기
    List<History> getHistoriesByStockOrderByHnoDesc(Stock stock);
}
