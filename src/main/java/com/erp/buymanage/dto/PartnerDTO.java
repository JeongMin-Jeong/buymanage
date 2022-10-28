package com.erp.buymanage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {

    private Long partnerindex;
    private String cpartnerno; // 납품업체 사업자등록번호
    private String cpartnername; // 납품업체 상호
    private String cpartnerceo; // 납품업체 대표자
    private String cpartneraddr; // 납품업체 주소
    private String cpartnerphone; // 납품업체 연락처
    private String cpartnerfax; // 납품업체 팩스

}
