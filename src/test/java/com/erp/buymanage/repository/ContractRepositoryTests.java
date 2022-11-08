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
        IntStream.rangeClosed(1,80).forEach(i -> {
//            Contract contract = Contract.builder()
//                    .ccode("C202210211000" + i)
//                    .cdate("2022-10-21")
//                    .cpartnername("거래처" + (i))
//                    .cpartnerceo("거래처 대표자")
//                    .cpartneraddr("거래처 주소")
//                    .cpartnerphone("거래처 연락처")
//                    .cpartnerfax("거래처 팩스")
//                    .pcode("P202210211000" + (i))
//                    .pname("품목이름" + (i))
//                    //.pcount(100+i)
//                    .pprice(i*10000)
//                    //.cmanager("인수자"+i)
//                    //.cpartnermanager("납품자"+i)
//                    //.cdeliverydate("2022-10-28")
//                    .cstatus("신규계약")
//                    .cetc("비고")
//                    .build();
//            System.out.println(repository.save(contract));
        });
    }
}
