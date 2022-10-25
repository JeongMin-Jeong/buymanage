package com.erp.buymanage.service;

import com.erp.buymanage.dto.OrderDTO;
import com.erp.buymanage.dto.OrderPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.entity.OrderEntity;

public interface OrderService {

    long register(OrderDTO dto);
    PageResultDTO<OrderDTO, OrderEntity> getList(OrderPageRequestDTO requestDTO);
    OrderDTO read(Long ono);

    default OrderEntity dtoToEntity(OrderDTO dto) {
        OrderEntity entity = OrderEntity.builder()
                .ono(dto.getOno())
                .deliverydate(dto.getDeliverydate())
                .orderdate(dto.getOrderdate())
                .duedate(dto.getDuedate())
                .omanager(dto.getOmanager())
                .opname(dto.getOpname())
                .oprice(dto.getOprice())
                .ocompany(dto.getOcompany())
                .oquantity(dto.getOquantity())
                .ostate(dto.getOstate())
                .oetc(dto.getOetc())
                .ocode(dto.getOcode())
                .ptype1(dto.getPtype1())
                .ptype2(dto.getPtype2())
                .pcode(dto.getPcode())
                .pcontent(dto.getPcontent())
                .ocode(dto.getOcode())
                .build();
            return entity;
    }
    default OrderDTO entityToDto(OrderEntity entity){
        OrderDTO dto = OrderDTO.builder()
                .ono(entity.getOno())
                .deliverydate(entity.getDeliverydate())
                .orderdate(entity.getOrderdate())
                .duedate(entity.getDuedate())
                .omanager(entity.getOmanager())
                .opname(entity.getOpname())
                .oprice(entity.getOprice())
                .ocompany(entity.getOcompany())
                .oquantity(entity.getOquantity())
                .ostate(entity.getOstate())
                .oetc(entity.getOetc())
                .ocode(entity.getOcode())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                .pcode(entity.getPcode())
                .pcontent(entity.getPcontent())
                .ocode(entity.getOcode())
                .build();
        return dto;

    }

    void remove(Long ono);

    void modify(OrderDTO dto);


}
