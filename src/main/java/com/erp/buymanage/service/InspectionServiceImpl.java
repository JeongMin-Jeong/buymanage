package com.erp.buymanage.service;

import com.erp.buymanage.dto.InspectionDTO;
import com.erp.buymanage.dto.InspectionPageRequestDTO;
import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.entity.Inspection;
import com.erp.buymanage.repository.InspectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Optional;

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

    @Override
    public PageResultDTO<InspectionDTO, Inspection> getList(InspectionPageRequestDTO inspectionPageRequestDTO) {
        return null;
    }

    @Override // 조회처리
    public InspectionDTO read(Long ono) {
        log.info(">>>>> InspectionServiceImpl(read)");
        Optional<Inspection> result = Optional.ofNullable(repository.findByOno(ono));
        return result.isPresent() ? entityToDto(result.get()) : null;
    }

    @Override // 삭제처리
    public void remove(Long ino) {
        log.info(">>>>> InspectionServiceImpl(remove)");
        repository.deleteById(ino);
    }

    @Override // 수정처리
    public void modify(InspectionDTO dto) {
        Optional<Inspection> result = Optional.ofNullable(repository.findByOno(dto.getOno()));
        if(result.isPresent()){
            Inspection entity = result.get();
            entity.changeDegree1(dto.getInspection_degree1());
            entity.changeDegree2(dto.getInspection_degree2());
            entity.changeEtc1(dto.getInspection_etc1());
            entity.changeEtc2(dto.getInspection_etc2());
            repository.save(entity);
        }
    }


}
