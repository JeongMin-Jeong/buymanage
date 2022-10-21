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

        IntStream.rangeClosed(31,40).forEach(i -> {

            Contract contract = Contract.builder()
                    .cdate("2022-10-21")
                    .ccode("계약" + i)
                    .cpartnername("거래처" + (i-20))
                    .cpartnerceo("거래처 대표자")
                    .cpartneraddr("거래처 주소")
                    .cpartnerphone("거래처 연락처")
                    .cpartnerfax("거래처 팩스")
                    .pcode("품목코드" + (i-20))
                    .pname("품목이름" + (i-20))
                    .pcount(i)
                    .pprice(i*10)
                    .cmanager("인수자1")
                    .cpartnermanager("납품자2")
                    .cdeliverydate("2022-10-28")
                    .cstatus("신규")
                    .cetc("비고")
                    .build();
            System.out.println(repository.save(contract));
        });
    }
}
