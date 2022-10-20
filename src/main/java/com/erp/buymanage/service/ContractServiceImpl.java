package com.erp.buymanage.service;

import com.erp.buymanage.dto.ContractDTO;
import com.erp.buymanage.dto.PageRequestDTO2;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.entity.Contract;
import com.erp.buymanage.repository.ContractRepository;
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

        log.info(">>>>> dto");

        log.info(">>>>> dtoToEntity");
        Contract entity = dtoToEntity(dto);

        log.info(">>>>> entity");

        log.info(">>>>> repository에 저장");
        repository.save(entity);

        log.info(">>>>> return entity.getCno()");
        return entity.getCno();
    }

    @Override // 목록처리
    public PageResultDTO<ContractDTO, Contract> getList(PageRequestDTO2 pageRequestDTO2) {

        Pageable pageable = pageRequestDTO2.getPageable(Sort.by("cno").descending());
//        BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색조건처리
        Page<Contract> result = repository.findAll(pageable); // Querydsl 사용
        Function<Contract, ContractDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override // 조회처리
    public ContractDTO read(Long cno) {
        Optional<Contract> result = repository.findById(cno);
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override // 삭제처리
    public void remove(Long cno) {
        repository.deleteById(cno);
    }

}
