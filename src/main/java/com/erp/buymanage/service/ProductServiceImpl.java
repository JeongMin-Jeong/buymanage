package com.erp.buymanage.service;

import com.erp.buymanage.dto.PageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.dto.ProductDTO;
import com.erp.buymanage.entity.Product;
import com.erp.buymanage.entity.QProduct;
import com.erp.buymanage.repository.ProductRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동주입
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    @Override // 등록처리
    public Long register(ProductDTO dto) {

        log.info(">>>>> dto");

        log.info(">>>>> dtoToEntity");
        Product entity = dtoToEntity(dto);

        log.info(">>>>> entity");

        log.info(">>>>> repository에 저장");
        repository.save(entity);

        log.info(">>>>> return entity.getPno()");
        return entity.getPno();
    }

    @Override // 목록처리
    public PageResultDTO<ProductDTO, Product> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
//        BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색조건처리
        Page<Product> result = repository.findAll(pageable); // Querydsl 사용
        Function<Product, ProductDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override // 조회처리
    public ProductDTO read(Long pno) {
        Optional<Product> result = repository.findById(pno);
        return result.isPresent() ? entityToDto(result.get()) : null;
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
