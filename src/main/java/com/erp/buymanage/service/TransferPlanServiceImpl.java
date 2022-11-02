package com.erp.buymanage.service;

import com.erp.buymanage.dto.*;
import com.erp.buymanage.entity.QOrderEntity;
import com.erp.buymanage.entity.QTransferPlan;
import com.erp.buymanage.entity.TransferPlan;
import com.erp.buymanage.repository.TransferPlanRepository;
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
public class TransferPlanServiceImpl implements TransferPlanService{

    private final TransferPlanRepository repository;

    @Override // 등록처리
    public Long register(TransferPlanDTO dto) {
        log.info(">>>>> TransferPlanServiceImpl(register)");

        TransferPlan entity = dtoToEntity(dto);
        log.info(">>>>>>>>>> dtoToEntity");

        repository.save(entity);
        log.info(">>>>>>>>>> repository에 저장");

        return entity.getTno();
    }

    @Override // 목록처리
    public PageResultDTO<TransferPlanDTO, TransferPlan> getList(TransferPlanPageRequestDTO transferPlanPageRequestDTO) {
        log.info(">>>>> TransferPlanServiceImpl(getList)");

        QTransferPlan qTransferPlan = QTransferPlan.transferPlan;
        Pageable pageable = transferPlanPageRequestDTO.getPageable(Sort.by("tno").descending());
        BooleanBuilder booleanBuilder = getSearch(transferPlanPageRequestDTO); // 검색조건처리
        booleanBuilder.and(qTransferPlan.tstate.eq("신규"));
        Page<TransferPlan> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<TransferPlan, TransferPlanDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(TransferPlanPageRequestDTO transferPlanPageRequestDTO) {
        log.info(">>>>> TransferPlanServiceImpl(getSearch)");

        String type = transferPlanPageRequestDTO.getType();
        if(type == ""){ type = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QTransferPlan qTransferPlan = QTransferPlan.transferPlan;
        String keyword = transferPlanPageRequestDTO.getKeyword();
        BooleanExpression expression = qTransferPlan.tno.gt(0L);   //sno > 0 조건만 생성
        booleanBuilder.and(expression);
        if(type == "") {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(type != null) {
            if (type.contains("pc")) { // 품목코드
                conditionBuilder.and(qTransferPlan.pcode.contains(keyword));
            }
            if (type.equals("pn")) { // 품목이름
                conditionBuilder.and(qTransferPlan.pname.contains(keyword));
            }
            if (type.equals("tp")) { // 자재소요공정
                conditionBuilder.and(qTransferPlan.tuseprocess.contains(keyword));
            }
        } else if (type == null) {

        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    @Override // 조회처리
    public TransferPlanDTO read(Long tno) {
        log.info(">>>>> TransferPlanServiceImpl(read)");

        Optional<TransferPlan> result = repository.findById(tno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override // 삭제처리
    public void remove(Long tno) {
        log.info(">>>>> TransferPlanServiceImpl(remove)");

        repository.deleteById(tno);
    }

    @Override // 수정처리
    public void modify(TransferPlanDTO dto) {
        log.info(">>>>> TransferPlanServiceImpl(modify)");

        Optional<TransferPlan> result = repository.findById(dto.getTno());

        if(result.isPresent()){
            TransferPlan entity = result.get();
            entity.changeTuseprocess(dto.getTuseprocess());
            entity.changeTusedate(dto.getTusedate());
            entity.changeTusecount(dto.getTusecount());
            entity.changeDeliverydate(dto.getDeliverydate());

            repository.save(entity);
        }
    }
}
