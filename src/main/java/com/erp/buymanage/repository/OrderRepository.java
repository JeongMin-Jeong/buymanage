package com.erp.buymanage.repository;

import com.erp.buymanage.entity.OrderEntity;
import com.erp.buymanage.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository <OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {

    Optional<OrderEntity> findByCno(Long cno);

    Optional<OrderEntity> findByOstateAndOrderdate(String ostate, String orderdate);

    List<OrderEntity> findByOstateContains(String ostate);

    @Query("SELECT o from OrderEntity o where o.ostate like '구매발주' and o.orderdate like : orderDate% ")
    List<OrderEntity> searchByorderDateStartsWith(@Param("orderDate") String orderDate);

}
