package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.TblCategory;
import com.erp.buymanage.entity.TblCategoryChild;

public interface CategoryService {
    Long register(TblCategoryDTO dto);
    Long register2(TblCategoryChildDTO dto2);

    PageResultDTO<TblCategoryDTO, TblCategory> getlist(CategoryPageRequestDTO categoryPageRequestDTO);
    PageResultDTO<TblCategoryChildDTO, TblCategoryChild> getlist2(CategoryPageRequestDTO categoryPageRequestDTO);

    default TblCategory dtoToEntity(TblCategoryDTO dto){
        TblCategory entity = TblCategory.builder()
                .cid(dto.getCid())
                .cname(dto.getCname())
                .build();
        return entity;
    }

    default TblCategoryDTO entityToDto(TblCategory entity){
        TblCategoryDTO dto = TblCategoryDTO.builder()
                .cid(entity.getCid())
                .cname(entity.getCname())
                .build();
        return dto;
    }

    default TblCategoryChild dtoToEntity(TblCategoryChildDTO dto2){
        TblCategoryChild entity2 = TblCategoryChild.builder()
                .ccid(dto2.getCcid())
                .ccname(dto2.getCcname())
                .build();
        return entity2;
    }

    default TblCategoryChildDTO entityToDto(TblCategoryChild entity2){
        TblCategoryChildDTO dto2 = TblCategoryChildDTO.builder()
                .ccid(entity2.getCcid())
                .ccname(entity2.getCcname())
                .build();
        return dto2;
    }

    TblCategoryDTO read(Long cid);
    void remove(Long cid);
    void modify(TblCategoryDTO dto);

}
