package com.erp.buymanage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TblCategoryChildDTO {
    private Long ccid;
    private Long pcid;
    private String ccname;
}
