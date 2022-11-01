package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Contract;
import com.erp.buymanage.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InspectionRepository extends JpaRepository<Inspection, Long>, QuerydslPredicateExecutor<Inspection> {
}
