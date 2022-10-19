package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.QTblCategory;
import com.erp.buymanage.entity.QTblCategoryChild;
import com.erp.buymanage.entity.TblCategory;
import com.erp.buymanage.entity.TblCategoryChild;
import com.erp.buymanage.repository.TblCategoryRepository;
import com.erp.buymanage.repository.TblCategoryRepositoryChild;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동주입
public class CategoryServiceImpl implements CategoryService {

    private final TblCategoryRepository repository; //의존성 자동주입
    private final TblCategoryRepositoryChild repositoryChild; //의존성 자동주입

    @Override
    public Long register(TblCategoryDTO dto){
        log.info("DTO1--------------------------------");
        log.info(dto);
        TblCategory entity = dtoToEntity(dto);
        log.info(entity);
        repository.save(entity);
        return entity.getCid();
    }

    @Override
    public Long register2(TblCategoryChildDTO dto2) {
        log.info("DTO2--------------------------------");
        log.info(dto2);
        TblCategoryChild entity2 = dtoToEntity(dto2);
        log.info(entity2);
        repositoryChild.save(entity2);
        return entity2.getCcid();
    }

    //    @Override
//    public PageResultDTO<GuestbookDTO, Guestbook> getlist(PageRequestDTO requestDTO) {
//        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
//        Page<Guestbook> result = repository.findAll(pageable);
//        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
//        return new PageResultDTO<>(result, fn);
//    }
    @Override
    public PageResultDTO<TblCategoryDTO, TblCategory> getlist(CategoryPageRequestDTO categoryPageRequestDTO) {
        Pageable pageable = categoryPageRequestDTO.getPageable(Sort.by("cid").ascending());
        BooleanBuilder booleanBuilder = getSearch(categoryPageRequestDTO); //검색 조건 처리
        Page<TblCategory> result = repository.findAll(booleanBuilder, pageable); //Querydsl 사용
        Function<TblCategory, TblCategoryDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<TblCategoryChildDTO, TblCategoryChild> getlist2(CategoryPageRequestDTO categoryPageRequestDTO) {
        Pageable pageable = categoryPageRequestDTO.getPageable(Sort.by("ccid").ascending());
        BooleanBuilder booleanBuilder = getSearch2(categoryPageRequestDTO); //검색 조건 처리
        Page<TblCategoryChild> result2 = repositoryChild.findAll(booleanBuilder, pageable); //Querydsl 사용
        Function<TblCategoryChild, TblCategoryChildDTO> fn2 = (entity2 -> entityToDto(entity2));
        return new PageResultDTO<>(result2, fn2);
    }

    @Override
    public TblCategoryDTO read(Long cid) {
        return null;
    }

    @Override
    public void remove(Long cid) {
    }

    @Override
    public void modify(TblCategoryDTO dto) {
    }

    private BooleanBuilder getSearch(CategoryPageRequestDTO categoryPageRequestDTO){
        String type = categoryPageRequestDTO.getType(); //타입객체생성 dto와 연결
        BooleanBuilder booleanBuilder = new BooleanBuilder();//동적쿼리를 생성 where절 사용
        QTblCategory qTblCategory = QTblCategory.tblCategory; //쿼리SQL이 자동으로 만든 객체를 연결
        String keyword = categoryPageRequestDTO.getKeyword(); //키워드객체
        BooleanExpression expression = qTblCategory.cid.gt(0L); // gno > 0 조건만 생성
        booleanBuilder.and(expression);//AND조건 연결
        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }
        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();//WHERE문 생성을 위한 변수선언
        if(type.contains("t")){
            conditionBuilder.or(qTblCategory.cname.contains(keyword));//제목 OR
        }
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder); //AND조건
        return booleanBuilder;
    }

    private BooleanBuilder getSearch2(CategoryPageRequestDTO categoryPageRequestDTO){
        String type = categoryPageRequestDTO.getType(); //타입객체생성 dto와 연결
        BooleanBuilder booleanBuilder = new BooleanBuilder();//동적쿼리를 생성 where절 사용
        QTblCategoryChild qTblCategoryChild = QTblCategoryChild.tblCategoryChild; //쿼리SQL이 자동으로 만든 객체를 연결
        String keyword = categoryPageRequestDTO.getKeyword(); //키워드객체
        BooleanExpression expression = qTblCategoryChild.ccid.gt(0L); // gno > 0 조건만 생성
        booleanBuilder.and(expression);//AND조건 연결
        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }
        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();//WHERE문 생성을 위한 변수선언
        if(type.contains("t")){
            conditionBuilder.or(qTblCategoryChild.ccname.contains(keyword));//제목 OR
        }
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder); //AND조건
        return booleanBuilder;
    }

}
