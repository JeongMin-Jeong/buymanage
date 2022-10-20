package com.erp.buymanage.repository;

import com.erp.buymanage.entity.TblCategoryChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TblCategoryRepositoryChild extends JpaRepository<TblCategoryChild, Long>, QuerydslPredicateExecutor<TblCategoryChild> {
//    Optional<TblCategory> findByName (String name);
//    Optional<TblCategory> findByBranchAndName (String branch, String name);
//    Boolean existsByBranchAndName(String branch, String name);
}