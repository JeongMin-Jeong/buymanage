package com.erp.buymanage.repository;

import com.erp.buymanage.entity.OrderEntity;
import com.erp.buymanage.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrderRepository extends JpaRepository <OrderEntity, Long>, QuerydslPredicateExecutor<OrderEntity> {

}
