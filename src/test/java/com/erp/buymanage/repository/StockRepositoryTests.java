package com.erp.buymanage.repository;

import com.erp.buymanage.entity.QStock;
import com.erp.buymanage.entity.Stock;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class StockRepositoryTests {

    @Autowired
    private StockRepository stockRepository;

//    @Test
//    public void insertDummies() {
//
//        IntStream.rangeClosed(1,300).forEach(i -> {
//
//            Stock stock = Stock.builder()
//                    .scode("P" + i)
//                    .sname("Name" + i)
//                    .scate1("대분류" + i)
//                    .scate2("중분류" + i)
//                    .sin(1 + i)
//                    .sout(i)
//                    .sreturn(0)
//                    .stock(0)
//                    .snote("No")
//                    .build();
//            System.out.println(stockRepository.save(stock));
//        });
//    }

    @Test
    public void updateTest() {

        Optional<Stock> result = stockRepository.findById(300L);

        if(result.isPresent()){

            Stock stock = result.get();

            stock.changeCode("P1055");

            stockRepository.save(stock);
        }
    }

    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("sno").descending());

        QStock qStock = QStock.stock1;

        String keyword = "6";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exCode = qStock.scode.contains(keyword);

        BooleanExpression exScate1 = qStock.scate1.contains(keyword);

        BooleanExpression exAll = exCode.or(exScate1);

        builder.and(exAll);

        builder.and(qStock.sno.gt(0L));

        Page<Stock> result = stockRepository.findAll(builder, pageable);

        result.stream().forEach(stock -> {
            System.out.println(stock);
        });
    }
}
