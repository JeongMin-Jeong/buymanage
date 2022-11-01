package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface InspectionRepository extends JpaRepository<Inspection, Long>, QuerydslPredicateExecutor<Inspection> {
}

