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
                .cpartnerno(dto.getCpartnerno())
                .cpartnername(dto.getCpartnername())
                .cpartnerceo(dto.getCpartnerceo())
                .cpartneraddr(dto.getCpartneraddr())
                .cpartnerphone(dto.getCpartnerphone())
                .cpartnerfax(dto.getCpartnerfax())
                .pcode(dto.getPcode())
                .pname(dto.getPname())
                .ptype1(dto.getPtype1())
                .ptype2(dto.getPtype2())
                .pprice(dto.getPprice())
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
                .cpartnerno(entity.getCpartnerno())
                .cpartnername(entity.getCpartnername())
                .cpartnerceo(entity.getCpartnerceo())
                .cpartneraddr(entity.getCpartneraddr())
                .cpartnerphone(entity.getCpartnerphone())
                .cpartnerfax(entity.getCpartnerfax())
                .pcode(entity.getPcode())
                .pname(entity.getPname())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                .pprice(entity.getPprice())
                .cstatus(entity.getCstatus())
                .cetc(entity.getCetc())
                .build();
        return dto;
    }
}
