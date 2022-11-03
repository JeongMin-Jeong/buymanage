package com.erp.buymanage.service;

import com.erp.buymanage.dto.OrderDTO;
import com.erp.buymanage.dto.OrderPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.entity.OrderEntity;
import com.querydsl.core.types.Order;

public interface OrderService {

    long register(OrderDTO dto);
    PageResultDTO<OrderDTO, OrderEntity> getList(OrderPageRequestDTO requestDTO);
    PageResultDTO<OrderDTO, OrderEntity> getList2(OrderPageRequestDTO requestDTO);
    PageResultDTO<OrderDTO, OrderEntity> getList3(OrderPageRequestDTO requestDTO);
    OrderDTO read(Long ono);
    OrderDTO read2(Long cno, String ocode);

    default OrderEntity dtoToEntity(OrderDTO dto) {
        OrderEntity entity = OrderEntity.builder()
                .ono(dto.getOno())
                .deliverydate(dto.getDeliverydate())
                .orderdate(dto.getOrderdate())
                .omanager(dto.getOmanager())
                .pname(dto.getPname())
                .pprice(dto.getPprice())
                .cpartnername(dto.getCpartnername())
                .oquantity(dto.getOquantity())
                .ostate(dto.getOstate())
                .oetc(dto.getOetc())
                .ocode(dto.getOcode())
                .ptype1(dto.getPtype1())
                .ptype2(dto.getPtype2())
                .pcode(dto.getPcode())
                .pcontent(dto.getPcontent())
                .ocode(dto.getOcode())
                .cno(dto.getCno())
                .build();
            return entity;
    }
    default OrderDTO entityToDto(OrderEntity entity){
        OrderDTO dto = OrderDTO.builder()
                .ono(entity.getOno())
                .deliverydate(entity.getDeliverydate())
                .orderdate(entity.getOrderdate())
                .omanager(entity.getOmanager())
                .pname(entity.getPname())
                .pprice(entity.getPprice())
                .cpartnername(entity.getCpartnername())
                .oquantity(entity.getOquantity())
                .ostate(entity.getOstate())
                .oetc(entity.getOetc())
                .ocode(entity.getOcode())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                .pcode(entity.getPcode())
                .pcontent(entity.getPcontent())
                .ocode(entity.getOcode())
                .cno(entity.getCno())
                .build();
        return dto;

    }

    void remove(Long ono);

    void modify(OrderDTO dto);

    void inputModify(OrderDTO dto);




}
