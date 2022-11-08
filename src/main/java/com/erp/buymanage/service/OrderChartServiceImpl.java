package com.erp.buymanage.service;

import com.erp.buymanage.dto.OrderChartDTO;
import com.erp.buymanage.dto.OrderDTO;
import com.erp.buymanage.dto.StockChartDTO;
import com.erp.buymanage.entity.OrderChart;
import com.erp.buymanage.entity.OrderEntity;
import com.erp.buymanage.entity.Stock;
import com.erp.buymanage.entity.StockChart;
import com.erp.buymanage.repository.OrderChartRepository;
import com.erp.buymanage.repository.OrderRepository;
import com.erp.buymanage.repository.StockChartRepository;

import com.erp.buymanage.repository.StockRepository;
import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderChartServiceImpl implements OrderChartService {


    private final OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getList2(String ym, String ocode) {
        List<OrderEntity> result = orderRepository.findAllByOrderdateStartsWithAndOcodeOrderByOrderdateAsc(ym, ocode);

        return result.stream().map(orderEntity -> entityToDTO(orderEntity)).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> chart(String orderdate) {
        List<OrderEntity> result = orderRepository.findAllByOrderdate(orderdate);

        return result.stream().map(orderEntity -> entityToDTO(orderEntity)).collect(Collectors.toList());
    }

    @Override
    public void chartRegister() {

        OrderDTO orderDTO = new OrderDTO();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String orderdate = formatter.format(now);
        List<OrderEntity> chartList = new ArrayList<>();

        List<OrderEntity> result = orderRepository.findAll();

        for (OrderEntity order : result) {

            orderDTO.setOcode(order.getOcode());
            orderDTO.setOrderdate(order.getOrderdate());

            OrderEntity orderEntity = dtoToEntity(orderDTO);
            chartList.add(orderEntity);
        }

        orderRepository.saveAll(chartList);
    }
//    @Override
//    public List<OrderEntity> ordercount(String ostate , String orderdate) {
//
//        List<OrderEntity> result = orderRepository.countbyostateandorderdate(String ostate , String orderdate);
//
//        return result.stream().map(orderEntity -> entityToDTO(orderEntity)).collect(Collectors.toList());
//
//
//
//        return null;
//
//
////
//    }
    @Override
    public OrderDTO read(Long ono) {
        Optional<OrderEntity> result = orderRepository.findById(ono);
        return result.isPresent()? entityToDTO(result.get()): null;
    }


}
