package com.erp.buymanage.repository;

import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.StockChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface StockChartRepository extends JpaRepository<StockChart, Long>, QuerydslPredicateExecutor<StockChart> {

    List<StockChart> findAllByDateStartsWithAndScodeOrderByDateAsc(String ym, String scode);

    List<StockChart> findAllByDate(String sdate);

}
