package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.StockDTO;
import com.erp.buymanage.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockServiceTests {

    @Autowired
    private StockService service;

    @Test
    public void testRegister() {

        StockDTO stockDTO = StockDTO.builder()
                .scode("Sample scode...")
                .sname("Sample sname...")
                .scate1("Sample scate1...")
                .scate2("Sample scate2...")
                .sin(1)
                .sout(2)
                .sreturn(3)
                .stock(0)
                .snote("Sample snote...")
                .build();

        System.out.println(service.register(stockDTO));

    }

    @Test
    public void testList() {

        StockPageRequestDTO pageRequestDTO = StockPageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<StockDTO, Stock> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPage());

        System.out.println("------------------------------------------");
        for (StockDTO stockDTO : resultDTO.getDtoList()) {
            System.out.println(stockDTO);
        }

        System.out.println("==========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));

    }

    @Test
    public void testSearch() {

        StockPageRequestDTO pageRequestDTO = StockPageRequestDTO.builder()
                .page(1)
                .size(10)
                .type1("데스크톱")
                .type2("CPU")
                .build();

        PageResultDTO<StockDTO, Stock> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV : " + resultDTO.isPrev());
        System.out.println("NEXT : " + resultDTO.isNext());
        System.out.println("TOTAL : " + resultDTO.getTotalPage());

        System.out.println("------------------------------------------");
        for (StockDTO stockDTO : resultDTO.getDtoList()) {
            System.out.println(stockDTO);
        }

        System.out.println("==========================================");
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }
}
