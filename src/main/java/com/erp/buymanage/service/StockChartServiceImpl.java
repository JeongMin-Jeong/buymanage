package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.Stock;
import com.erp.buymanage.entity.StockChart;
import com.erp.buymanage.repository.StockChartRepository;
import com.erp.buymanage.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class StockChartServiceImpl implements StockChartService {

    private final StockChartRepository stockChartRepository;

    private final StockRepository stockRepository;

    @Override
    public List<StockChartDTO> getList2(String ym, String scode) {
        List<StockChart> result = stockChartRepository.findAllByDateStartsWithAndScodeOrderByDateAsc(ym, scode);

        return result.stream().map(stockChart -> entityToDTO(stockChart)).collect(Collectors.toList());
    }

    @Override
    public List<StockChartDTO> chart(String sdate) {
        List<StockChart> result = stockChartRepository.findAllByDate(sdate);

        return result.stream().map(stockChart -> entityToDTO(stockChart)).collect(Collectors.toList());
    }

    @Override
    public void chartRegister() {

        StockChartDTO stockChartDTO = new StockChartDTO();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String sdate = formatter.format(now);
        List<StockChart> chartList = new ArrayList<>();

        List<Stock> result = stockRepository.findAll();

        for (Stock stock : result) {
            stockChartDTO.setAmount(stock.getSreturn()*stock.getStock());
            stockChartDTO.setScode(stock.getScode());
            stockChartDTO.setDate(sdate);

            StockChart stockChart = dtoToEntity(stockChartDTO);
            chartList.add(stockChart);
        }

        stockChartRepository.saveAll(chartList);
    }
}
