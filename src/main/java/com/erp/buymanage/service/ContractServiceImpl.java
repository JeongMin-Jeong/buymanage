package com.erp.buymanage.service;

import com.erp.buymanage.dto.ContractDTO;
import com.erp.buymanage.dto.ContractPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.entity.Contract;
import com.erp.buymanage.entity.QContract;
import com.erp.buymanage.repository.ContractRepository;
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
public class ContractServiceImpl implements ContractService{

    private final ContractRepository repository;

    @Override // 등록처리
    public Long register(ContractDTO dto) {
        log.info(">>>>> ContractServiceImpl(register)");

        Contract entity = dtoToEntity(dto);
        log.info(">>>>>>>>>> dtoToEntity");

        repository.save(entity);
        log.info(">>>>>>>>>> repository에 저장");

        return entity.getCno();
    }

    @Override // 목록처리
    public PageResultDTO<ContractDTO, Contract> getList(ContractPageRequestDTO contractPageRequestDTO) {
        log.info(">>>>> ContractServiceImpl(getList)");

        Pageable pageable = contractPageRequestDTO.getPageable(Sort.by("cno").descending());
        BooleanBuilder booleanBuilder = getSearch(contractPageRequestDTO); // 검색조건처리
        Page<Contract> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
        Function<Contract, ContractDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder getSearch(ContractPageRequestDTO contractPageRequestDTO) {
        log.info(">>>>> ContractServiceImpl(getSearch)");

        String ctype1 = contractPageRequestDTO.getCtype1();
        String ctype2 = contractPageRequestDTO.getCtype2();
        if(ctype1 == ""){ ctype1 = null; }
        if(ctype2 == ""){ ctype2 = null; }

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QContract qContract = QContract.contract;
        String keyword = contractPageRequestDTO.getKeyword();
        BooleanExpression expression = qContract.cno.gt(0L);   //sno > 0 조건만 생성
        booleanBuilder.and(expression);
        if(ctype1 == "" && ctype2 == "") {
            return booleanBuilder;
        }

        //검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if(ctype1 != null && ctype2 != null) {
            if (ctype2.contains("cc")) {
                conditionBuilder.and(qContract.ccode.contains(keyword));
            }
            if (ctype2.equals("pc")) {
                conditionBuilder.and(qContract.pcode.contains(keyword));
            }
            if (ctype2.equals("pn")) {
                conditionBuilder.and(qContract.pname.contains(keyword));
            }
            if (ctype2.contains("cp")) {
                conditionBuilder.and(qContract.cpartnername.contains(keyword));
            }
            conditionBuilder.and(qContract.cstatus.eq(ctype1));
        } else if (ctype1 == null && ctype2 != null) {
            if (ctype2.contains("cc")) {
                conditionBuilder.and(qContract.ccode.contains(keyword));
            }
            if (ctype2.equals("pc")) {
                conditionBuilder.and(qContract.pcode.contains(keyword));
            }
            if (ctype2.equals("pn")) {
                conditionBuilder.and(qContract.pname.contains(keyword));
            }
            if (ctype2.contains("cp")) {
                conditionBuilder.and(qContract.cpartnername.contains(keyword));
            }
        } else if (ctype1 != null && ctype2 == null) {
            conditionBuilder.and(qContract.cstatus.eq(ctype1));
        } else if (ctype1 == null && ctype2 == null){

        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    @Override // 조회처리
    public ContractDTO read(Long cno) {
        log.info(">>>>> ContractServiceImpl(read)");

        Optional<Contract> result = repository.findById(cno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override // 삭제처리
    public void remove(Long cno) {
        log.info(">>>>> ContractServiceImpl(remove)");

        repository.deleteById(cno);
    }

    @Override // 수정처리
    public void modify(ContractDTO dto) {
        log.info(">>>>> ContractServiceImpl(modify)");

        Optional<Contract> result = repository.findById(dto.getCno());

        if(result.isPresent()){
            Contract entity = result.get();
            entity.changeCstatus(dto.getCstatus());

            repository.save(entity);
        }
    }

    @Override // 목록처리
    public PageResultDTO<ContractDTO, Contract> getList2(ContractPageRequestDTO contractPageRequestDTO) {
        log.info(">>>>> ContractServiceImpl(getList2)");

        //Pageable pageable = contractPageRequestDTO.getPageable(Sort.by("cno").descending());
        Pageable pageable = contractPageRequestDTO.getPageable(Sort.by("cno").descending());
        //BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색조건처리
        BooleanBuilder booleanBuilder = getSearch(contractPageRequestDTO); // 검색조건처리
        //Page<Contract> result = repository.findAll(pageable); // Querydsl 사용
        Page<Contract> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용

        Function<Contract, ContractDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

}
