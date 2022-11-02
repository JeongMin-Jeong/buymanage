package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.TransferPlan;

public interface TransferPlanService {
    // 등록처리
    Long register(TransferPlanDTO dto);

    // 목록처리
    PageResultDTO<TransferPlanDTO, TransferPlan> getList(TransferPlanPageRequestDTO transferPlanPageRequestDTO);

    // 조회처리
    TransferPlanDTO read(Long tno);

    // 삭제처리
    void remove(Long tno);

    // 수정처리
    void modify(TransferPlanDTO dto);

    void completeModify(TransferPlanDTO dto);

    // dto -> 엔티티
    default TransferPlan dtoToEntity(TransferPlanDTO dto){
        TransferPlan entity = TransferPlan.builder()
                .tno(dto.getTno())
                .pcode(dto.getPcode())
                .pname(dto.getPname())
                .tuseprocess(dto.getTuseprocess())
                .tusedate(dto.getTusedate())
                .tusecount(dto.getTusecount())
                .deliverydate(dto.getDeliverydate())
                .tstate(dto.getTstate())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default TransferPlanDTO entityToDto(TransferPlan entity){
        TransferPlanDTO dto = TransferPlanDTO.builder()
                .tno(entity.getTno())
                .pcode(entity.getPcode())
                .pname(entity.getPname())
                .tuseprocess(entity.getTuseprocess())
                .tusedate(entity.getTusedate())
                .tusecount(entity.getTusecount())
                .deliverydate(entity.getDeliverydate())
                .tstate(entity.getTstate())
                .build();
        return dto;
    }
}
