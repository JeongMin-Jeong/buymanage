package com.erp.buymanage.service;

import com.erp.buymanage.dto.StockPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.StockDTO;
import com.erp.buymanage.entity.QStock;
import com.erp.buymanage.entity.Stock;
import com.erp.buymanage.repository.StockRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor    //의존성 자동 주입
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Long register(StockDTO dto) {

        log.info("DTO---------------------");
        log.info(dto);

        Stock entity = dtoToEntity(dto);

        log.info(entity);

        stockRepository.save(entity);

        return entity.getSno();
    }

    @Override
    public PageResultDTO<StockDTO, Stock> getList(StockPageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("sno").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO); //검색 조건 처리

        Page<Stock> result = stockRepository.findAll(booleanBuilder, pageable);

        Function<Stock, StockDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public StockDTO read(Long sno) {

        Optional<Stock> result = stockRepository.findById(sno);

        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    public void modify(StockDTO dto) {

        Optional<Stock> result = stockRepository.findById(dto.getSno());

        if(result.isPresent()){

            Stock entity = result.get();

            entity.changeCode(dto.getScode());
            entity.changeName(dto.getSname());
            entity.changeCate1(dto.getScate1());
            entity.changeCate2(dto.getScate2());
            entity.changeNote(dto.getSnote());

            stockRepository.save(entity);
        }
    }

    @Override
    public void remove(Long sno) {

        stockRepository.deleteById(sno);

    }

    private BooleanBuilder getSearch(StockPageRequestDTO requestDTO) {   //Querydsl  처리

        String type1 = requestDTO.getType1();
        String type2 = requestDTO.getType2();
        String type3 = requestDTO.getType3();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QStock qStock = QStock.stock1;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qStock.sno.gt(0L);   //sno > 0 조건만 생성

        booleanBuilder.and(expression);

        if(type1 == "" && type2 == "" && type3 == "") {

            return booleanBuilder;

        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type1 != null && type2 != null && type3 != null) {
            conditionBuilder.and(qStock.scate1.eq(type1));
            conditionBuilder.and(qStock.scate2.eq(type2));
            if (type3.contains("k")) {
                conditionBuilder.or(qStock.scode.contains(keyword));
            }
            if (type3.contains("l")) {
                conditionBuilder.or(qStock.sname.contains(keyword));
            }
        }

        if(type1 != null || type2 != null) {
            conditionBuilder.and(qStock.scate1.eq(type1));
            conditionBuilder.and(qStock.scate2.eq(type2));
        }

        if(type1 == "" && type2 == "") {
            if (type3.contains("k")) {
                conditionBuilder.or(qStock.scode.contains(keyword));
            }
            if (type3.contains("l")) {
                conditionBuilder.or(qStock.sname.contains(keyword));
            }
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }
}
