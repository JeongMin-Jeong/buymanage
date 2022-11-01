package com.erp.buymanage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class
ContractDTO {

    private Long cno; // 자동번호부여

    private String cdate; // 계약일자
    private String ccode; // 계약번호
    private String cpartnerno; // 납품업체 사업자등록번호
    private String cpartnername; // 납품업체 상호
    private String cpartnerceo; // 납품업체 대표자
    private String cpartneraddr; // 납품업체 주소
    private String cpartnerphone; // 납품업체 연락처
    private String cpartnerfax; // 납품업체 팩스
    private String pcode; // 품목코드
    private String pname; // 품목이름
    private String ptype1; // 대분류
    private String ptype2; // 중분류
    private int pprice; // 품목단가
    private String cstatus; // 계약상태(신규,완료,종료)
    private String cetc; // 비고(양도,양수조건)
    private LocalDateTime regdate, moddate; // 등록일, 수정일
}
