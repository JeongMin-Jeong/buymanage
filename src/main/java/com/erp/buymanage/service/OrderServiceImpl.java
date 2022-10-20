package com.erp.erp_buymanage.service;


import com.erp.erp_buymanage.dto.OrderDTO;
import com.erp.erp_buymanage.dto.PageRequestDTO;
import com.erp.erp_buymanage.dto.PageResultDTO;
import com.erp.erp_buymanage.entity.OrderEntity;
import com.erp.erp_buymanage.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    public PageResultDTO<OrderDTO, OrderEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("ono").descending());

        Page<OrderEntity> result = repository.findAll(pageable);

        Function<OrderEntity,OrderDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result,fn);

    }
    @Override
    public OrderDTO read(long ono) {
        Optional<OrderEntity> result = repository.findById(ono);

        return result.isPresent()? entityToDto(result.get()): null;

    }
    @Override
    public void remove( Long ono) {

        repository.deleteById(ono);

    }

    @Override
    public void modify(OrderDTO dto) {
        Optional<OrderEntity> result = repository.findById(dto.getOno());

        if(result.isPresent())  {
            OrderEntity entity = result.get();
            entity.changePcode(dto.getPcode());
            entity.changePcontent(dto.getPcontent());
            entity.changePtype1(dto.getPtype1());
            entity.changePtype2(dto.getPtype2());
            entity.changeduedate(dto.getDuedate());
            entity.changeOcompany(dto.getOcompany());
            entity.changeDeliverydate(dto.getDeliverydate());
            entity.changeOmanager(dto.getOmanager());
            entity.changeOpname(dto.getOpname());
            entity.changeOetc(dto.getOetc());
            entity.changeOprice(dto.getOprice());
            entity.changeOstate(dto.getOstate());
            entity.changeOquantity(dto.getOquantity());
            entity.changeOrderdate(dto.getOrderdate());


                    repository.save(entity);
        }

    }

}
