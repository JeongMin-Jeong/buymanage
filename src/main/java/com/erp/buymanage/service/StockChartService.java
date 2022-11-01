package com.erp.buymanage.service;


import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.StockChart;

import java.util.List;

public interface StockChartService {

    List<StockChartDTO> getList(String month, String scode);

    default StockChart dtoToEntity(StockChartDTO dto) {
        StockChart entity = StockChart.builder()
                .cno(dto.getCno())
                .date(dto.getDate())
                .amount(dto.getAmount())
                .scode(dto.getScode())
                .build();
        return entity;
    }

    default StockChartDTO entityToDTO(StockChart entity) {
        StockChartDTO dto = StockChartDTO.builder()
                .cno(entity.getCno())
                .date(entity.getDate())
                .amount(entity.getAmount())
                .scode(entity.getScode())
                .build();
        return dto;
    }

}
