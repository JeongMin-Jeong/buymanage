package com.erp.buymanage.service;

import com.erp.buymanage.dto.ContractPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ContractDTO;
import com.erp.buymanage.entity.Contract;

public interface ContractService {
    // 등록처리
    Long register(ContractDTO dto);

    // 목록처리
    PageResultDTO<ContractDTO, Contract> getList(ContractPageRequestDTO contractPageRequestDTO);
    PageResultDTO<ContractDTO, Contract> getList2(ContractPageRequestDTO contractPageRequestDTO);
    // 조회처리
    //ContractDTO read(Long pno); //2022-10-24 JJH pno->cno
    ContractDTO read(Long cno);

    // 삭제처리
    //void remove(Long pno); //2022-10-24 JJH pno->cno
    void remove(Long cno);


    // 수정처리
    void modify(ContractDTO dto);

    // dto -> 엔티티
    default Contract dtoToEntity(ContractDTO dto){
        Contract entity = Contract.builder()
                .cno(dto.getCno())
                .cdate(dto.getCdate())
                .ccode(dto.getCcode())
                .cpartnername(dto.getCpartnername())
                .cpartnerceo(dto.getCpartnerceo())
                .cpartneraddr(dto.getCpartneraddr())
                .cpartnerphone(dto.getCpartnerphone())
                .cpartnerfax(dto.getCpartnerfax())
                .pcode(dto.getPcode())
                .pname(dto.getPname())
                .ptype1(dto.getPtype1())
                .ptype2(dto.getPtype2())
                .pcount(dto.getPcount())
                .pprice(dto.getPprice())
                .cmanager(dto.getCmanager())
                .cpartnermanager(dto.getCpartnermanager())
                .cdeliverydate(dto.getCdeliverydate())
                .cstatus(dto.getCstatus())
                .cetc(dto.getCetc())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default ContractDTO entityToDto(Contract entity){
        ContractDTO dto = ContractDTO.builder()
                .cno(entity.getCno())
                .cdate(entity.getCdate())
                .ccode(entity.getCcode())
                .cpartnername(entity.getCpartnername())
                .cpartnerceo(entity.getCpartnerceo())
                .cpartneraddr(entity.getCpartneraddr())
                .cpartnerphone(entity.getCpartnerphone())
                .cpartnerfax(entity.getCpartnerfax())
                .pcode(entity.getPcode())
                .pname(entity.getPname())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                .pcount(entity.getPcount())
                .pprice(entity.getPprice())
                .cmanager(entity.getCmanager())
                .cpartnermanager(entity.getCpartnermanager())
                .cdeliverydate(entity.getCdeliverydate())
                .cstatus(entity.getCstatus())
                .cetc(entity.getCetc())
                .build();
        return dto;
    }
}
