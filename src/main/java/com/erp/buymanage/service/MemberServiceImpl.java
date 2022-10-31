package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.QTblMember;
import com.erp.buymanage.entity.TblMember;
import com.erp.buymanage.repository.MemberRepository;
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
@RequiredArgsConstructor // 의존성 자동주입
public class MemberServiceImpl implements MemberService{

    private final MemberRepository repository;

    @Override
    public PageResultDTO<MemberDTO, TblMember> getList(MemberPageRequestDTO memberPageRequestDTO) {
        log.info(">>>>> ContractServiceImpl(getList)");
        Pageable pageable = memberPageRequestDTO.getPageable(Sort.by("cno").descending());
        BooleanBuilder booleanBuilder = getSearch(memberPageRequestDTO); // 검색조건처리
        Page<TblMember> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<TblMember, MemberDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(MemberPageRequestDTO memberPageRequestDTO) {
        log.info(">>>>> MemberServiceImpl(getSearch)");

        String ctype1 = memberPageRequestDTO.getCtype1();
        String ctype2 = memberPageRequestDTO.getCtype2();
        if(ctype1 == ""){ ctype1 = null; }
        if(ctype2 == ""){ ctype2 = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QTblMember qTblMember = QTblMember.tblMember;
        String keyword = memberPageRequestDTO.getKeyword();
        BooleanExpression expression = qTblMember.email.isNotEmpty();   //sno > 0 조건만 생성
        booleanBuilder.and(expression);
        if(ctype1 == null && ctype2 == null) {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(ctype1 != null && ctype2 != null) {
            if (ctype2.contains("cc")) {
                conditionBuilder.and(qTblMember.email.contains(keyword));
            }
            conditionBuilder.and(qTblMember.email.eq(ctype1));
        } else if (ctype1 == null && ctype2 != null) {
            if (ctype2.contains("cc")) {
                conditionBuilder.and(qTblMember.email.contains(keyword));
            }
        } else if (ctype1 != null && ctype2 == null) {
            conditionBuilder.and(qTblMember.email.eq(ctype1));
        } else if (ctype1 == null && ctype2 == null){

        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

}
