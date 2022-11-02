package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name= "tbl_partner")
public class Partner{

    @Id
    private Long partnerindex;

    @Column(length = 100, nullable = false)
    private String cpartnerno; // 납품업체 사업자등록번호

    @Column(length = 100, nullable = false)
    private String cpartnername; // 납품업체 상호

    @Column(length = 100, nullable = false)
    private String cpartnerceo; // 납품업체 대표자

    @Column(length = 255, nullable = false)
    private String cpartneraddr; // 납품업체 주소

    @Column(length = 100, nullable = false)
    private String cpartnerphone; // 납품업체 연락처

    @Column(length = 100, nullable = false)
    private String cpartnerfax; // 납품업체 팩스

}
