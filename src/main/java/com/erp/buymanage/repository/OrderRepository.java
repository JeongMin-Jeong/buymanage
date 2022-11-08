package com.erp.buymanage.repository;

import com.erp.buymanage.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository <OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {

@Transactional
@Modifying



    public OrderEntity findByOcode(String ocode);
    Optional<OrderEntity> findByCno(Long cno);

//    Optional<OrderEntity> findByOstateAndOrderdate(String ostate, String orderdate);
//
//    List<OrderEntity> findByOstateContains(String ostate);

//    @Query("SELECT o from OrderEntity o where o.ostate like '구매발주' and o.orderdate like : orderDate% ")
//    List<OrderEntity> searchByorderDateStartsWith(@Param("orderDate") String orderDate);


    Optional<OrderEntity> findByCnoAndOcode(Long cno, String ocode);

//    Long countbyostateandorderdate(String ostate , String orderdate);
List<OrderEntity> findAllByOrderdateStartsWithAndOcodeOrderByOrderdateAsc(String ym, String ocode);

    List<OrderEntity> findAllByOrderdate(String orderdate);

//    List<OrderEntity> countbyostateandorderdate(String ostate , String orderdate);

//    @Query(value =  "select count(o) from OrderEntity o where o.ostate = '마감완료' and o.orderdate= :orderdate")
//    List<OrderEntity> countbyostateandorderdate(@Param("orderdate") String orderdate);


    @Query(value =  "select count(o) from tbl_order o where o.ostate = '구매발주' and o.orderdate=:orderdate")
    public long countbyostateandorderdate(@Param("orderdate") String orderdate);
}
