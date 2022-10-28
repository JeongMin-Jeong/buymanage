package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.Partner;

public interface PartnerService {

    // 납품업체 목록처리
    PageResultDTO<PartnerDTO, Partner> getList(PartnerPageRequestDTO partnerPageRequestDTO);

    // dto -> 엔티티
    default Partner dtoToEntity(PartnerDTO dto){
        Partner entity = Partner.builder()
                .partnerindex(dto.getPartnerindex())
                .cpartnerno(dto.getCpartnerno())
                .cpartnername(dto.getCpartnername())
                .cpartnerceo(dto.getCpartnerceo())
                .cpartneraddr(dto.getCpartneraddr())
                .cpartnerphone(dto.getCpartnerphone())
                .cpartnerfax(dto.getCpartnerfax())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default PartnerDTO entityToDto(Partner entity){
        PartnerDTO dto = PartnerDTO.builder()
                .partnerindex(entity.getPartnerindex())
                .cpartnerno(entity.getCpartnerno())
                .cpartnername(entity.getCpartnername())
                .cpartnerceo(entity.getCpartnerceo())
                .cpartneraddr(entity.getCpartneraddr())
                .cpartnerphone(entity.getCpartnerphone())
                .cpartnerfax(entity.getCpartnerfax())
                .build();
        return dto;
    }

}
