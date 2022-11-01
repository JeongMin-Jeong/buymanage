package com.erp.buymanage.service;

import com.erp.buymanage.dto.InspectionDTO;
import com.erp.buymanage.dto.InspectionPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.entity.Inspection;
import com.erp.buymanage.entity.QInspection;
import com.erp.buymanage.repository.InspectionRepository;
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
public class InspectionServiceImpl implements InspectionService{

    private final InspectionRepository repository;

    @Override // 등록처리
    public Long register(InspectionDTO dto) {
        log.info(">>>>> InspectionServiceImpl(register)");
        Inspection entity = dtoToEntity(dto);
        log.info(">>>>>>>>>> dtoToEntity");
        repository.save(entity);
        log.info(">>>>>>>>>> repository에 저장");
        return entity.getIno();
    }

    @Override // 목록처리
    public PageResultDTO<InspectionDTO, Inspection> getList(InspectionPageRequestDTO InspectionPageRequestDTO) {
        log.info(">>>>> InspectionServiceImpl(getList)");

        Pageable pageable = InspectionPageRequestDTO.getPageable(Sort.by("cno").descending());
        BooleanBuilder booleanBuilder = getSearch(InspectionPageRequestDTO); // 검색조건처리
        Page<Inspection> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<Inspection, InspectionDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(InspectionPageRequestDTO InspectionPageRequestDTO) {
        log.info(">>>>> InspectionServiceImpl(getSearch)");

        String ctype1 = InspectionPageRequestDTO.getCtype1();
        String ctype2 = InspectionPageRequestDTO.getCtype2();
        if(ctype1 == ""){ ctype1 = null; }
        if(ctype2 == ""){ ctype2 = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QInspection qInspection = QInspection.inspection;
        String keyword = InspectionPageRequestDTO.getKeyword();
        BooleanExpression expression = qInspection.ino.gt(0L);   //sno > 0 조건만 생성
        booleanBuilder.and(expression);
        if(ctype1 == "" && ctype2 == "") {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(ctype1 != null && ctype2 != null) {
            if (ctype2.contains("cc")) {
                //conditionBuilder.and(qInspection.ccode.contains(keyword));
            }
            //conditionBuilder.and(qInspection.cstatus.eq(ctype1));
        } else if (ctype1 == null && ctype2 != null) {
            if (ctype2.contains("cc")) {
                //conditionBuilder.and(qInspection.ccode.contains(keyword));
            }
        } else if (ctype1 != null && ctype2 == null) {
            //conditionBuilder.and(qInspection.cstatus.eq(ctype1));
        } else if (ctype1 == null && ctype2 == null){

        }
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    @Override // 조회처리
    public InspectionDTO read(Long cno) {
        log.info(">>>>> InspectionServiceImpl(read)");

        Optional<Inspection> result = repository.findById(cno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override // 삭제처리
    public void remove(Long cno) {
        log.info(">>>>> InspectionServiceImpl(remove)");

        repository.deleteById(cno);
    }

    @Override // 수정처리
    public void modify(InspectionDTO dto) {
        log.info(">>>>> InspectionServiceImpl(modify)");

        Optional<Inspection> result = repository.findById(dto.getIno());

        if(result.isPresent()){
            Inspection entity = result.get();
            //entity.changeCstatus(dto.getCstatus());
            repository.save(entity);
        }
    }

}
