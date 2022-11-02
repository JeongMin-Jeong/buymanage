package com.erp.buymanage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryDTO {

    private Long hno;
    private String text;
    private String requester;
    private Long sno;   //stock 번호
    private LocalDateTime regDate, modDate;
}
