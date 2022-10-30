package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockChartDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StockChartServiceTests {

    @Autowired
    private StockChartService stockChartService;

    @Test
    public void testGetList(){
        String month = "10";
        String socde = "P9999";

        List<StockChartDTO> list = stockChartService.getList(month, socde);

        list.forEach(stockChartDTO -> System.out.println(stockChartDTO));
    }
}
