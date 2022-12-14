package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.StockDTO;
import com.erp.buymanage.entity.Stock;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StockService {
    Long register(StockDTO dto);

    PageResultDTO<StockDTO, Stock> getList(StockPageRequestDTO requestDTO);

    StockDTO read(Long sno);

    void remove(Long sno);

    void modify(StockDTO dto);

    void outModify(StockDTO dto);

    Object getStock(HttpServletResponse response);

    void createExcelDownloadResponse(HttpServletResponse response, List<Stock> stockList);

    default Stock dtoToEntity(StockDTO dto) {
        Stock entity = Stock.builder()
                .sno(dto.getSno())
                .scode(dto.getScode())
                .sname(dto.getSname())
                .scate1(dto.getScate1())
                .scate2(dto.getScate2())
                .sin(dto.getSin())
                .sout(dto.getSout())
                .sreturn(dto.getSreturn())
                .stock(dto.getStock())
                .snote(dto.getSnote())
                .requester(dto.getRequester())
                .build();
        return entity;
    }

    default StockDTO entityToDto(Stock entity) {

        StockDTO dto = StockDTO.builder()
                .sno(entity.getSno())
                .scode(entity.getScode())
                .sname(entity.getSname())
                .scate1(entity.getScate1())
                .scate2(entity.getScate2())
                .sin(entity.getSin())
                .sout(entity.getSout())
                .sreturn(entity.getSreturn())
                .stock(entity.getStock())
                .snote(entity.getSnote())
                .requester(entity.getRequester())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
