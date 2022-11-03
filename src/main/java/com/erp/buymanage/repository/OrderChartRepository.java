package com.erp.buymanage.repository;

import com.erp.buymanage.entity.OrderChart;
import com.erp.buymanage.entity.StockChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OrderChartRepository extends JpaRepository<OrderChart, Long>, QuerydslPredicateExecutor<OrderChart> {

    List<OrderChart> findAllByDateContainsAndOrderstate(String month, String orderState);

    Long countbyostateandorderdate(String ostate , String orderdate);
}
