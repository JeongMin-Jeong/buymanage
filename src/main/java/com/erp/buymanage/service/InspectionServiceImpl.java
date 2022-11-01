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
        Optional<Inspection> result = repository.findById(ono);
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
