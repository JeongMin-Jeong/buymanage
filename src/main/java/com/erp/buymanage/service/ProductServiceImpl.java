package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.*;
import com.erp.buymanage.repository.ProductImageRepository;
import com.erp.buymanage.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동주입
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ProductImageRepository imageRepository;

    @Transactional
    @Override // 등록처리
    public Long register(ProductDTO dto) {
        log.info(">>>>> ProductServiceImpl (register)");

        Map<String , Object> entityMap = dtoToEntity(dto);
        Product entity =(Product) entityMap.get("product");
        List<ProductImage> productImageList= (List<ProductImage>) entityMap.get("imgList");
        log.info(">>>>>>>>>> ProductService (dtoToEntity)");

        repository.save(entity);
        log.info(">>>>>>>>>> ProductRepository 저장");
        productImageList.forEach(imageEntity -> {
            imageRepository.save(imageEntity);
        });
        log.info(">>>>>>>>>> ProductImageRepository 저장");

        return entity.getPno();
    }

//    @Override // 목록처리 (이미지출력)
//    public PageResultDTO<ProductDTO, Object[]> getList(ProductPageRequestDTO productPageRequestDTO) {
//        log.info(">>>>> ProductServiceImpl (getList)");
//
//        Pageable pageable = productPageRequestDTO.getPageable(Sort.by("pno").descending());
//        Page<Object[]> result = repository.getListPage(pageable);
//
//        Function<Object[], ProductDTO> fn = (arr -> entityToDto(
//                (Product)arr[0] ,
//                (List<ProductImage>)(Arrays.asList((ProductImage)arr[1]))
//        ));
//        log.info(">>>>>>>>>> ProductService (entityToDto)");
//
//        return new PageResultDTO<>(result, fn);
//    }
    @Override // 목록처리 (이미지출력 안함)
    public PageResultDTO<ProductDTO, Product> getList(ProductPageRequestDTO productPageRequestDTO) {
        log.info(">>>>> ContractServiceImpl(getList)");

        Pageable pageable = productPageRequestDTO.getPageable(Sort.by("pno").descending());
        BooleanBuilder booleanBuilder = getSearch(productPageRequestDTO); // 검색조건처리
        Page<Product> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<Product, ProductDTO> fn = (entity -> entityToDto2(entity));
        log.info(">>>>>>>>>> entityToDto");
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(ProductPageRequestDTO productPageRequestDTO) {
        System.out.println(">>>>> ProductServiceImpl (getSearch)");
        String ptype1 = productPageRequestDTO.getPtype1();
        String ptype2 = productPageRequestDTO.getPtype2();
        String ptype3 = productPageRequestDTO.getPtype3();
        if(ptype1 == ""){ ptype1 = null; }
        if(ptype2 == ""){ ptype2 = null; }
        if(ptype3 == ""){ ptype3 = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct qProduct = QProduct.product;
        String keyword = productPageRequestDTO.getKeyword();
        BooleanExpression expression = qProduct.pno.gt(0L);   //sno > 0 조건만 생성
        booleanBuilder.and(expression);
        if(ptype1 == "" && ptype2 == "" && ptype3 == "") {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(ptype2 != null && ptype3 != null) {
            if (ptype3.contains("pc")) {
                conditionBuilder.and(qProduct.pcode.contains(keyword));
            }
            if (ptype3.equals("pn")) {
                conditionBuilder.and(qProduct.pname.contains(keyword));
            }
            conditionBuilder.and(qProduct.ptype2.eq(ptype2));
        } else if (ptype2 == null && ptype3 != null) {
            if (ptype3.contains("pc")) {
                conditionBuilder.and(qProduct.pcode.contains(keyword));
            }
            if (ptype3.equals("pn")) {
                conditionBuilder.and(qProduct.pname.contains(keyword));
            }
        } else if (ptype2 != null && ptype3 == null) {
            conditionBuilder.and(qProduct.ptype2.eq(ptype2));
        } else if (ptype2 == null && ptype3 == null){

        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    @Override // 조회처리
    public ProductDTO read(Long pno) {
        log.info(">>>>> ProductServiceImpl (read)");

        List<Object[]> result = repository.getProductWithAll(pno);
        Product entity = (Product) result.get(0)[0];

        List<ProductImage> productImageList = new ArrayList<>();
        result.forEach(arr -> {
            ProductImage productImage = (ProductImage)arr[1];
            productImageList.add(productImage);
        });
        return entityToDto(entity, productImageList);
    }

    @Override // 삭제처리
    public void remove(Long pno) {
        log.info(">>>>> ProductServiceImpl (remove)");

        repository.deleteById(pno);
    }
}