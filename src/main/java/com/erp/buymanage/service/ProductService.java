package com.erp.buymanage.service;

import com.erp.buymanage.dto.PageRequestDTO2;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.entity.Product;

public interface ProductService {
    // 등록처리
    Long register(ProductDTO dto);

    // 목록처리
    PageResultDTO<ProductDTO, Product> getList(PageRequestDTO2 requestDTO);

    // 조회처리
    ProductDTO read(Long pno);

    // 삭제처리
    void remove(Long pno);

    // 수정처리
    void modify(ProductDTO dto);

    // dto -> 엔티티
    default Product dtoToEntity(ProductDTO dto){
        Product entity = Product.builder()
                .pno(dto.getPno())
                .pcode(dto.getPcode())
                .pname(dto.getPname())
                .ptype1(dto.getPtype1())
                .ptype2(dto.getPtype2())
                .pcontent(dto.getPcontent())
                .petc(dto.getPetc())
                .build();
        return entity;
    }

    // 엔티티 -> dto
    default ProductDTO entityToDto(Product entity){
        ProductDTO dto = ProductDTO.builder()
                .pno(entity.getPno())
                .pcode(entity.getPcode())
                .pname(entity.getPname())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                .pcontent(entity.getPcontent())
                .petc(entity.getPetc())
                .regdate(entity.getRegDate())
                .moddate(entity.getModDate())
                .build();
        return dto;
    }
}
