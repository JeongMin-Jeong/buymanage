package com.erp.buymanage.service;

import com.erp.buymanage.dto.ProductPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.entity.Product;
import com.erp.buymanage.entity.ProductImage;
import com.erp.buymanage.entity.QProduct;
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
//        log.info(">>>>> ProductServiceImpl (register)");
//        log.info(">>>>> dto");
//        log.info(">>>>> dtoToEntity");
//        Product entity = dtoToEntity(dto);
//        log.info(">>>>> entity");
//        log.info(">>>>> repository에 저장");
//        repository.save(entity);
//        log.info(">>>>> return entity.getPno()");
//        return entity.getPno();

        Map<String , Object> entityMap = dtoToEntity(dto);
        Product entity =(Product) entityMap.get("product");
        List<ProductImage> productImageList= (List<ProductImage>) entityMap.get("imgList");
        log.info(">>>>> Product,entity dtoToEntity");

        repository.save(entity);
        log.info(">>>>> ProductRepository 저장");
        productImageList.forEach(imageEntity -> {
            imageRepository.save(imageEntity);
        });
        log.info(">>>>> ProductImageRepository 저장");

        return entity.getPno();
    }

    //    @Override // 목록처리
//    public PageResultDTO<ProductDTO, Product> getList(ProductPageRequestDTO productPageRequestDTO) {
//        Pageable pageable = productPageRequestDTO.getPageable(Sort.by("pno").descending());
//        BooleanBuilder booleanBuilder = getSearch(productPageRequestDTO); // 검색조건처리
//        Page<Product> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
//        Function<Product, ProductDTO> fn = (entity -> entityToDto(entity));
//        return new PageResultDTO<>(result, fn);
//    }
    @Override
    public PageResultDTO<ProductDTO, Object[]> getList(ProductPageRequestDTO productPageRequestDTO) {
        Pageable pageable = productPageRequestDTO.getPageable(Sort.by("pno").descending());

        Page<Object[]> result = repository.getListPage(pageable);

        Function<Object[], ProductDTO> fn = (arr -> entityToDto(
                (Product)arr[0] ,
                (List<ProductImage>)(Arrays.asList((ProductImage)arr[1]))
        ));

        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(ProductPageRequestDTO productPageRequestDTO) {
        String ptype1 = productPageRequestDTO.getPtype1();
        String ptype2 = productPageRequestDTO.getPtype2();
        String ptype3 = productPageRequestDTO.getPtype3();

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

        if(ptype1 != null && ptype2 != null && ptype3 != null){
            conditionBuilder.and(qProduct.ptype1.eq(ptype1));
            conditionBuilder.and(qProduct.ptype2.eq(ptype2));
            if (ptype3.contains("a")) {
                conditionBuilder.or(qProduct.pcode.contains(keyword));
            }
            if (ptype3.contains("b")) {
                conditionBuilder.or(qProduct.pname.contains(keyword));
            }
            if (ptype3.contains("c")) {
                conditionBuilder.or(qProduct.pcontent.contains(keyword));
            }
        }

        if(ptype1 != null || ptype2 != null) {
            conditionBuilder.and(qProduct.ptype1.eq(ptype1));
            conditionBuilder.and(qProduct.ptype2.eq(ptype2));
            //conditionBuilder.and(qProduct.ptype3.eq(ptype3));
        }

        if(ptype3 != null) {
            if (ptype3.contains("a")) {
                conditionBuilder.or(qProduct.pcode.contains(keyword));
            }
            if (ptype3.contains("b")) {
                conditionBuilder.or(qProduct.pname.contains(keyword));
            }
            if (ptype3.contains("c")) {
                conditionBuilder.or(qProduct.pcontent.contains(keyword));
            }

        }
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;
    }

    //    @Override // 조회처리
//    public ProductDTO read(Long pno) {
//        Optional<Product> result = repository.findById(pno);
//        return result.isPresent() ? entityToDto(result.get()) : null;
//    }
    @Override
    public ProductDTO read(Long pno) {
        List<Object[]> result = repository.getMovieWithAll(pno);

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
        repository.deleteById(pno);
    }

    @Override // 수정처리
    public void modify(ProductDTO dto) {
        Optional<Product> result = repository.findById(dto.getPno());
        if(result.isPresent()){
            Product entity = result.get();
            entity.changePname(dto.getPname());
            entity.changePtype1(dto.getPtype1());
            entity.changePtype2(dto.getPtype2());
            entity.changePcontent(dto.getPcontent());
            entity.changePetc(dto.getPetc());
            repository.save(entity);
        }
    }
}