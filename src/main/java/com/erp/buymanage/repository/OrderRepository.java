package com.erp.erp_buymanage.repository;

import com.erp.erp_buymanage.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <OrderEntity, Long> {

}
