package com.erp.buymanage.security.repository;

import com.erp.buymanage.entity.TblMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<TblMember, String>, QuerydslPredicateExecutor<TblMember>{
}



