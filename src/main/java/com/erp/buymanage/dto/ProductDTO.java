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
public class ProductDTO {

    private Long pno; // 자동번호부여

    private String pcode; // 품목코드
    private String pname; // 품목이름
    private String ptype1; // 대분류
    private String ptype2; // 중분류
    private String pcontent; // 설명
    private String petc; // 비고
    private LocalDateTime regdate, moddate; // 등록일, 수정일
}
