package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PartnerRepository extends JpaRepository<Partner, Long>, QuerydslPredicateExecutor<Partner> {
}
