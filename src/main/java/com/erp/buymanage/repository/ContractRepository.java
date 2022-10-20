package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ContractRepository extends JpaRepository<Contract, Long>,
        QuerydslPredicateExecutor<Contract> {
}
