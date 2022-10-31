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
public class TransferPlanDTO {
    private Long tno; // 자동번호부여
    private String pcode; // 품목코드
    private String pname; // 품목이름
    private String tuseprocess; // 자재소요공정
    private String tusedate; // 자재소요일정
    private String tusecount; // 자재소요량
    private String deliverydate; // 조달납기
    private LocalDateTime regdate, moddate; // 등록일, 수정일
}
