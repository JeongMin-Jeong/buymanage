package com.erp.buymanage.service;


import com.erp.buymanage.dto.OrderChartDTO;
import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.OrderChart;
import com.erp.buymanage.entity.StockChart;

import java.util.List;

public interface OrderChartService {

    List<OrderChartDTO> getList(String month, String orderstate);

    default OrderChart dtoToEntity(OrderChartDTO dto) {
        OrderChart entity = OrderChart.builder()
                .ono(dto.getOno())
                .date(dto.getDate())
                .amount(dto.getAmount())
                .orderstate(dto.getOrderstate())

                .build();
        return entity;
    }

    default OrderChartDTO entityToDTO(OrderChart entity) {
        OrderChartDTO dto = OrderChartDTO.builder()
                .ono(entity.getOno())
                .date(entity.getDate())
                .amount(entity.getAmount())
                .orderstate(entity.getOrderstate())
                .build();
        return dto;
    }

}
