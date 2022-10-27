package com.erp.buymanage.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name= "tbl_contract")
public class Contract extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    @Column(length = 100, nullable = false)
    private String cdate; // 계약일자

    @Column(length = 100, nullable = false)
    private String ccode; // 계약번호

    @Column(length = 100, nullable = false)
    private String cpartnername; // 거래처명

    @Column(length = 100, nullable = false)
    private String cpartnerceo; // 거래처 대표자

    @Column(length = 255, nullable = false)
    private String cpartneraddr; // 거래처 주소

    @Column(length = 100, nullable = false)
    private String cpartnerphone; // 거래처 연락처

    @Column(length = 100, nullable = false)
    private String cpartnerfax; // 거래처 팩스

    @Column(length = 100, nullable = false)
    private String pcode; // 품목코드

    @Column(length = 100, nullable = false)
    private String pname; // 품목이름

    @Column(length = 50, nullable = false)
    private String ptype1; // 대분류

    @Column(length = 50, nullable = false)
    private String ptype2; // 중분류

    @Column(length = 100, nullable = false)
    private int pcount; // 품목수량

    @Column(length = 100, nullable = false)
    private int pprice; // 품목단가

    @Column(length = 100, nullable = false)
    private String cmanager; // 인수자

    @Column(length = 100, nullable = false)
    private String cpartnermanager; // 납품자

    @Column(length = 100, nullable = false)
    private String cdeliverydate; // 납기일자

    @Column(length = 100, nullable = false)
    private String cstatus; // 계약상태(신규,완료,종료)

    @Column(length = 255, nullable = false)
    private String cetc; // 비고(양도,양수조건)

    public void changeCstatus(String cstatus){
        this.cstatus = cstatus;
    }
}
