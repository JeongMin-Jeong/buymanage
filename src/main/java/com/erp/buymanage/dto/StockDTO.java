package com.erp.buymanage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockDTO {

    private Long sno;
    private String scode;
    private String sname;
    private String scate1;
    private String scate2;
    private int sin;
    private int sout;
    private int sreturn;
    private int stock;
    private String snote;
    private String requester;
    private LocalDateTime regDate, modDate;
}
