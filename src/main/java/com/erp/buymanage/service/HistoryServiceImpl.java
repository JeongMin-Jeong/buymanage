package com.erp.buymanage.service;

import com.erp.buymanage.dto.HistoryDTO;
import com.erp.buymanage.entity.History;
import com.erp.buymanage.entity.Stock;
import com.erp.buymanage.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class HistoryServiceImpl implements HistoryService{

    private final HistoryRepository historyRepository;

    @Override
    public void register(HistoryDTO historyDTO) {

        History history = dtoToEntity(historyDTO);

        historyRepository.save(history);
    }

    @Override
    public List<HistoryDTO> getList(Long sno) {

        List<History> result = historyRepository.getHistoriesByStockOrderByHno(Stock.builder().sno(sno).build());

        return result.stream().map(history -> entityToDTO(history)).collect(Collectors.toList());
    }
}
