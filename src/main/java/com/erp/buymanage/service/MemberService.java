package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.TblMember;

public interface MemberService {

    PageResultDTO<MemberDTO, TblMember> getList(MemberPageRequestDTO memberPageRequestDTO);

    default TblMember dtoToEntity(MemberDTO dto){
        TblMember entity = TblMember.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .fromSocial(dto.getFrom_social())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default MemberDTO entityToDto(TblMember entity){
        MemberDTO dto = MemberDTO.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .password(entity.getPassword())
                .from_social(entity.isFromSocial())
                .build();
        return dto;
    }
}
