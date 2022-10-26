package com.erp.buymanage.service;

import com.erp.buymanage.dto.HistoryDTO;
import com.erp.buymanage.entity.History;
import com.erp.buymanage.entity.Stock;

import java.util.List;

public interface HistoryService {

    void register(HistoryDTO historyDTO);   //내역 등록

    List<HistoryDTO> getList(Long sno);     //특정 자재의 내역 목록

    //HistoryDTO를 History 객체로 변환 Stock 객체의 처리가 수반
    default History dtoToEntity(HistoryDTO historyDTO){

        Stock stock = Stock.builder().sno(historyDTO.getSno()).build();

        History history = History.builder()
                .hno(historyDTO.getHno())
                .text(historyDTO.getText())
                .requester(historyDTO.getRequester())
                .stock(stock)
                .build();

        return history;
    }

    //History 객체를 HistoryDTO로 변환 Stock 객체가 필요하지 않으므로 게시물 번호만
    default HistoryDTO entityToDTO(History history){

        HistoryDTO dto = HistoryDTO.builder()
                .hno(history.getHno())
                .text(history.getText())
                .requester(history.getRequester())
                .regDate(history.getRegDate())
                .modDate(history.getModDate())
                .build();

        return dto;
    }
}
