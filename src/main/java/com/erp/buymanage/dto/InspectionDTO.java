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
public class InspectionDTO {
    private Long ino; // 자동번호부여
    private Long ono_fk;
    private String inspection_date1;
    private int inspection_degree1;
    private String inspection_prime1;
    private String inspection_etc1;
    private String inspection_date2;
    private int inspection_degree2;
    private String inspection_prime2;
    private String inspection_etc2;
}
