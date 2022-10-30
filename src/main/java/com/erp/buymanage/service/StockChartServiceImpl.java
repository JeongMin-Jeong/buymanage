package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.StockChart;
import com.erp.buymanage.repository.StockChartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockChartServiceImpl implements StockChartService {

    private final StockChartRepository stockChartRepository;

    @Override
    public List<StockChartDTO> getList(String month, String scode) {

        List<StockChart> result = stockChartRepository.findAllByDateContainsAndScode(month, scode);

        return result.stream().map(stockChart -> entityToDTO(stockChart)).collect(Collectors.toList());
    }
}
