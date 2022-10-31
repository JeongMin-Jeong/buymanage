package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.Contract;
import com.erp.buymanage.entity.Product;
import com.erp.buymanage.entity.ProductImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProductService {
    // 등록처리
    Long register(ProductDTO dto);

    // 목록처리
//    PageResultDTO<ProductDTO, Object[]> getList(ProductPageRequestDTO productPageRequestDTO);
    PageResultDTO<ProductDTO, Product> getList(ProductPageRequestDTO productPageRequestDTO);
//    PageResultDTO<ProductDTO, Product> getList2(ProductPageRequestDTO productPageRequestDTO);

    // 조회처리
    ProductDTO read(Long pno);

    // 삭제처리
    void remove(Long pno);

    // 수정처리
//    void modify(ProductDTO dto);

    // dto -> 엔티티
    default Map<String, Object> dtoToEntity(ProductDTO dto){
        Map<String, Object> entityMap = new HashMap<>();

        Product entity = Product.builder()
                .pno(dto.getPno())
                .pcode(dto.getPcode())
                .pname(dto.getPname())
                .ptype1(dto.getPtype1())
                .ptype2(dto.getPtype2())
                //.ptype3(dto.getPtype3())
                .pcontent(dto.getPcontent())
                .petc(dto.getPetc())
                .build();
        entityMap.put("product", entity);

        List<ProductImageDTO> imageDTOList = dto.getImageDTOList();
        if(imageDTOList != null && imageDTOList.size() > 0) {
            List<ProductImage> productImageList = imageDTOList.stream().map(productImageDTO ->
            {
                ProductImage productImage = ProductImage.builder()
                        .path(productImageDTO.getPath())
                        .imgName(productImageDTO.getImgName())
                        .uuid(productImageDTO.getUuid())
                        .product(entity)
                        .build();
                return productImage;
            }).collect(Collectors.toList());  //객체를 새로운 리스트로 만드는 방법
            entityMap.put("imgList", productImageList);
        }
        return entityMap;
    }

    // 엔티티 -> dto
    default ProductDTO entityToDto(Product entity, List<ProductImage> productImages){
        ProductDTO dto = ProductDTO.builder()
                .pno(entity.getPno())
                .pcode(entity.getPcode())
                .pname(entity.getPname())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                //.ptype3(entity.getPtype3())
                .pcontent(entity.getPcontent())
                .petc(entity.getPetc())
                .regdate(entity.getRegDate())
                .moddate(entity.getModDate())
                .build();

        List<ProductImageDTO> imageDTOList = productImages.stream().map(productImage -> {
            return ProductImageDTO.builder()
                    .imgName(productImage.getImgName())
                    .path(productImage.getPath())
                    .uuid(productImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        dto.setImageDTOList(imageDTOList);
        return dto;
    }
    default ProductDTO entityToDto2(Product entity){
        ProductDTO dto = ProductDTO.builder()
                .pno(entity.getPno())
                .pcode(entity.getPcode())
                .pname(entity.getPname())
                .ptype1(entity.getPtype1())
                .ptype2(entity.getPtype2())
                //.ptype3(entity.getPtype3())
                .pcontent(entity.getPcontent())
                .petc(entity.getPetc())
                .regdate(entity.getRegDate())
                .moddate(entity.getModDate())
                .build();
        return dto;
    }
}
