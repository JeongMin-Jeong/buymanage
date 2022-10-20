package com.erp.buymanage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TblCategoryDTO {
    private Long cid;
    private String cname;
}
