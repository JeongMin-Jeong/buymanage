package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.Partner;
import com.erp.buymanage.entity.QPartner;
import com.erp.buymanage.repository.PartnerRepository;
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
public class PartnerServiceImpl implements PartnerService{

    private final PartnerRepository repository;

    @Override
    public PageResultDTO<PartnerDTO, Partner> getList(PartnerPageRequestDTO partnerPageRequestDTO) {
        log.info(">>>>> PartnerServiceImpl (getList)");

        Pageable pageable = partnerPageRequestDTO.getPageable(Sort.by("partnerindex").descending());
        BooleanBuilder booleanBuilder = getSearch(partnerPageRequestDTO); // 검색조건처리
        Page<Partner> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<Partner, PartnerDTO> fn = (entity -> entityToDto(entity));
        log.info(">>>>>>>>>> entityToDto");
        return new PageResultDTO<>(result, fn);
    }
    private BooleanBuilder getSearch(PartnerPageRequestDTO partnerPageRequestDTO) {
        System.out.println(">>>>> PartnerServiceImpl (getSearch)");

        String type = partnerPageRequestDTO.getType();
        if(type == ""){ type = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QPartner qPartner = QPartner.partner;
        String keyword = partnerPageRequestDTO.getKeyword();
        BooleanExpression expression = qPartner.partnerindex.gt(0L);   //sno > 0 조건만 생성
        booleanBuilder.and(expression);
        if(type == "") {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type != null) {
            if (type.contains("no")) {
                conditionBuilder.and(qPartner.cpartnerno.contains(keyword));
            }
            if (type.contains("name")) {
                conditionBuilder.and(qPartner.cpartnername.contains(keyword));
            }
        }  else if (type == null){

        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }
}
