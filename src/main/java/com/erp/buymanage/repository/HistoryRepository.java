package com.erp.buymanage.repository;

import com.erp.buymanage.entity.History;
import com.erp.buymanage.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long>, QuerydslPredicateExecutor<History> {

    //Stock 삭제 시 댓글들 삭제
    @Modifying
    @Query("delete from History h where h.stock.sno = :sno")
    public void deleteBySno(Long sno);

    //Stock으로 내역 목록 가져오기
    List<History> getHistoriesByStockOrderByHno(Stock stock);
}
