package com.erp.buymanage.repository;

import com.erp.buymanage.entity.TransferPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TransferPlanRepository extends JpaRepository<TransferPlan, Long>, QuerydslPredicateExecutor<TransferPlan> {
}
