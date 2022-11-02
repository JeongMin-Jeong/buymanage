package com.erp.buymanage.service;

import com.erp.buymanage.dto.OrderChartDTO;
import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.OrderChart;
import com.erp.buymanage.entity.StockChart;
import com.erp.buymanage.repository.OrderChartRepository;
import com.erp.buymanage.repository.StockChartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderChartServiceImpl implements OrderChartService {
    private final OrderChartRepository orderChartRepository;

    @Override
    public List<OrderChartDTO> getList(String month, String orderstate) {

        List<OrderChart> result = orderChartRepository.findAllByDateContainsAndOrderstate(month, orderstate);

        return result.stream().map(orderChart -> entityToDTO(orderChart)).collect(Collectors.toList());
    }
}
