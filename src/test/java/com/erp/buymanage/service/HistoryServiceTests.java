package com.erp.buymanage.service;

import com.erp.buymanage.dto.HistoryDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HistoryServiceTests {

    @Autowired
    private HistoryService service;

    @Test
    public void testGetList() {

        Long sno = 6L;

        List<HistoryDTO> historyDTOList = service.getList(sno);

        historyDTOList.forEach(historyDTO -> System.out.println(historyDTO));

    }

}
