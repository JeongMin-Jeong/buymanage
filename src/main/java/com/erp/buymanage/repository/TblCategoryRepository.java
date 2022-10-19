package com.erp.buymanage.repository;

import com.erp.buymanage.entity.TblCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TblCategoryRepository extends JpaRepository<TblCategory, Long>, QuerydslPredicateExecutor<TblCategory> {
//    Optional<TblCategory> findByName (String name);
//    Optional<TblCategory> findByBranchAndName (String branch, String name);
//    Boolean existsByBranchAndName(String branch, String name);
}