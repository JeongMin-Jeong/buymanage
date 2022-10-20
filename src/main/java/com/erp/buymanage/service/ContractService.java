package com.erp.buymanage.service;

import com.erp.buymanage.dto.PageRequestDTO2;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ContractDTO;
import com.erp.buymanage.entity.Contract;

public interface ContractService {
    // 등록처리
    Long register(ContractDTO dto);

    // 목록처리
    PageResultDTO<ContractDTO, Contract> getList(PageRequestDTO2 pageRequestDTO2);

    // 조회처리
    ContractDTO read(Long pno);

    // 삭제처리
    void remove(Long pno);

    // 수정처리
//    void modify(ContractDTO dto);

    // dto -> 엔티티
    default Contract dtoToEntity(ContractDTO dto){
        Contract entity = Contract.builder()
                .cno(dto.getCno())
                .ccode(dto.getCcode())
                .cpartnername(dto.getCpartnername())
                .cpartnerceo(dto.getCpartnerceo())
                .cpartneraddr(dto.getCpartneraddr())
                .cpartnerphone(dto.getCpartnerphone())
                .cpartnerfax(dto.getCpartnerfax())
                .pcode(dto.getPcode())
                .pcount(dto.getPcount())
                .pprice(dto.getPprice())
                .pname(dto.getPname())
                .cmanager(dto.getCmanager())
                .cpartnermanager(dto.getCpartnermanager())
                .cdate(dto.getCdate())
                .cstatus(dto.getCstatus())
                .cetc(dto.getCetc())
                .cleadtime(dto.getCleadtime())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default ContractDTO entityToDto(Contract entity){
        ContractDTO dto = ContractDTO.builder()
                .cno(entity.getCno())
                .ccode(entity.getCcode())
                .cpartnername(entity.getCpartnername())
                .cpartnerceo(entity.getCpartnerceo())
                .cpartneraddr(entity.getCpartneraddr())
                .cpartnerphone(entity.getCpartnerphone())
                .cpartnerfax(entity.getCpartnerfax())
                .pcode(entity.getPcode())
                .pcount(entity.getPcount())
                .pprice(entity.getPprice())
                .pname(entity.getPname())
                .cmanager(entity.getCmanager())
                .cpartnermanager(entity.getCpartnermanager())
                .cdate(entity.getCdate())
                .cstatus(entity.getCstatus())
                .cetc(entity.getCetc())
                .cleadtime(entity.getCleadtime())
                .build();
        return dto;
    }
}
