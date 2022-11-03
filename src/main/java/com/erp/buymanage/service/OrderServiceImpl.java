package com.erp.buymanage.service;


import com.erp.buymanage.dto.ContractPageRequestDTO;
import com.erp.buymanage.dto.OrderDTO;
import com.erp.buymanage.dto.OrderPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.entity.OrderEntity;
import com.erp.buymanage.entity.QContract;
import com.erp.buymanage.entity.QOrderEntity;
import com.erp.buymanage.entity.Stock;
import com.erp.buymanage.repository.OrderRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public long register(OrderDTO dto) {
        log.info("DTO----------------");
        log.info(dto);
        OrderEntity entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getOno();
    }

    @Override
    public PageResultDTO<OrderDTO, OrderEntity> getList(OrderPageRequestDTO orderPageRequestDTO) {
        Pageable pageable = orderPageRequestDTO.getPageable(Sort.by("ono").descending());
        BooleanBuilder booleanBuilder = getSearch(orderPageRequestDTO); // 검색조건처리
        //Page<OrderEntity> result = repository.findAll(pageable);
        Page<OrderEntity> result = repository.findAll(booleanBuilder, pageable);
        Function<OrderEntity, OrderDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<OrderDTO, OrderEntity> getList2(OrderPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("ono").descending());
        QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qOrderEntity.ostate.eq("검수완료"));
        Page<OrderEntity> result = repository.findAll(booleanBuilder, pageable);
        Function<OrderEntity,OrderDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result,fn);
    }

    @Override
    public PageResultDTO<OrderDTO, OrderEntity> getList3(OrderPageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("ono").descending());
        QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qOrderEntity.ostate.eq("마감완료"));
        Page<OrderEntity> result = repository.findAll(booleanBuilder, pageable);
        Function<OrderEntity,OrderDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result,fn);
    }

    private BooleanBuilder getSearch(OrderPageRequestDTO orderPageRequestDTO) {
        log.info(">>>>> OrderServiceImpl(getSearch)");
        String otype1 = orderPageRequestDTO.getOtype1();
        String otype2 = orderPageRequestDTO.getOtype2();
        if(otype1 == ""){ otype1 = null; }
        if(otype2 == ""){ otype2 = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QOrderEntity qOrderEntity = QOrderEntity.orderEntity;
        String keyword = orderPageRequestDTO.getKeyword();
        BooleanExpression expression = qOrderEntity.ono.gt(0L);   //ono > 0 조건만 생성
        booleanBuilder.and(expression);
        if(otype1 == null && otype2 == null) {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(otype1 != null && otype2 != null) {
            if (otype2.equals("oc")) {
                conditionBuilder.and(qOrderEntity.ocode.contains(keyword));
            }
            if (otype2.equals("pn")) {
                conditionBuilder.and(qOrderEntity.pname.contains(keyword));
            }
            if (otype2.equals("om")) {
                conditionBuilder.and(qOrderEntity.omanager.contains(keyword));
            }
            if (otype2.equals("cp")) {
                conditionBuilder.and(qOrderEntity.cpartnername.contains(keyword));
            }
            conditionBuilder.and(qOrderEntity.ostate.eq(otype1));
        } else if (otype1 == null && otype2 != null) {
            if (otype2.contains("oc")) {
                conditionBuilder.and(qOrderEntity.ocode.contains(keyword));
            }
            if (otype2.equals("pn")) {
                conditionBuilder.and(qOrderEntity.pname.contains(keyword));
            }
            if (otype2.equals("om")) {
                conditionBuilder.and(qOrderEntity.omanager.contains(keyword));
            }
            if (otype2.equals("cp")) {
                conditionBuilder.and(qOrderEntity.cpartnername.contains(keyword));
            }
        } else if (otype1 != null && otype2 == null) {
            conditionBuilder.and(qOrderEntity.ostate.eq(otype1));
        } else if (otype1 == null && otype2 == null){

        }
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    @Override
    public OrderDTO read(Long ono) {
        Optional<OrderEntity> result = repository.findById(ono);
        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public OrderDTO read2(Long cno, String ocode) {
        Optional<OrderEntity> result = repository.findByCnoAndOcode(cno, ocode);
        return result.isPresent()? entityToDto(result.get()): null;
    }

    @Override
    public void remove(Long ono) {
        repository.deleteById(ono);
    }

    @Override
    public void modify(OrderDTO dto) {
        Optional<OrderEntity> result = repository.findById(dto.getOno());
        if(result.isPresent())  {
            OrderEntity entity = result.get();
            entity.changeOcode(dto.getOcode());
            entity.changePcontent(dto.getPcontent());
            entity.changePtype1(dto.getPtype1());
            entity.changePtype2(dto.getPtype2());

            entity.changeCpartnername(dto.getCpartnername());
            entity.changeDeliverydate(dto.getDeliverydate());
            entity.changeOmanager(dto.getOmanager());
            entity.changePname(dto.getPname());
            entity.changeOetc(dto.getOetc());
            entity.changePprice(dto.getPprice());
            entity.changeOstate(dto.getOstate());
            entity.changeOquantity(dto.getOquantity());
            entity.changeOrderdate(dto.getOrderdate());
            repository.save(entity);
        }
    }

    @Override
    public void inputModify(OrderDTO dto) {
        Optional<OrderEntity> result = repository.findById(dto.getOno());
        if(result.isPresent()) {
            OrderEntity entity = result.get();
            entity.changeOstate(dto.getOstate());
            repository.save(entity);
        }
    }



    public void ostateCount(OrderDTO dto) {
       Long count = repository.countbyostateandorderdate(dto.getOstate(),dto.getOrderdate());




    }

}
