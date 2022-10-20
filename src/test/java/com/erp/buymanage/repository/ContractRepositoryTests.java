package com.erp.buymanage.repository;

import com.erp.buymanage.entity.Contract;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class ContractRepositoryTests {

    @Autowired
    private ContractRepository repository;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Contract contract = Contract.builder()
                    .ccode(i + "-" + i + "-" + i)
                    .cpartnername("납품업체 상호")
                    .cpartnerceo("납품업체 대표이름")
                    .cpartneraddr("납품업체 주소")
                    .cpartnerphone("납품업체 전화번호")
                    .cpartnerfax("납품업체 팩스번호")
                    .pcode("품목코드" + i)
                    .pcount("품목수량" + i)
                    .pprice("품목단가" + i)
                    .pname("품목이름" + i)
                    .cmanager("인수자")
                    .cpartnermanager("납품자")
                    .cdate("계약일자")
                    .cstatus("계약상태(신규,완료,종료)")
                    .cetc("비고")
                    .cleadtime("리드타임")
                    .build();
            System.out.println(repository.save(contract));
        });
    }
}
