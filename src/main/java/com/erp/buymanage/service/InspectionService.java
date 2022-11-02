package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.Inspection;

public interface InspectionService {
    // 등록처리
    Long register(InspectionDTO dto);

    // 목록처리
    PageResultDTO<InspectionDTO, Inspection> getList(InspectionPageRequestDTO inspectionPageRequestDTO);

    // 조회처리
    //ContractDTO read(Long pno); //2022-10-24 JJH pno->cno
    InspectionDTO read(Long order_ono);

    // 삭제처리
    void remove(Long ino);

    // 수정처리
    void modify(InspectionDTO dto);

    // dto -> 엔티티
    default Inspection dtoToEntity(InspectionDTO dto){
        Inspection entity = Inspection.builder()
                .ino(dto.getIno())
                .inspection_date1(dto.getInspection_date1())
                .inspection_date2(dto.getInspection_date2())
                .inspection_degree1(dto.getInspection_degree1())
                .inspection_degree2(dto.getInspection_degree2())
                .inspection_prime1(dto.getInspection_prime1())
                .inspection_prime2(dto.getInspection_prime2())
                .inspection_etc1(dto.getInspection_etc1())
                .inspection_etc2(dto.getInspection_etc2())
                .ono(dto.getOno())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default InspectionDTO entityToDto(Inspection entity){
        InspectionDTO dto = InspectionDTO.builder()
                .ino(entity.getIno())
                .inspection_date1(entity.getInspection_date1())
                .inspection_date2(entity.getInspection_date2())
                .inspection_degree1(entity.getInspection_degree1())
                .inspection_degree2(entity.getInspection_degree2())
                .inspection_prime1(entity.getInspection_prime1())
                .inspection_prime2(entity.getInspection_prime2())
                .inspection_etc1(entity.getInspection_etc1())
                .inspection_etc2(entity.getInspection_etc2())
                .ono(entity.getOno())
                .build();
        return dto;
    }
}
