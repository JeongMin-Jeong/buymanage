package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name= "tbl_transferPlan")
public class TransferPlan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    @Column(nullable = false)
    private String pcode; // 품목코드

    @Column(nullable = false)
    private String pname; // 품목이름

    @Column(nullable = false)
    private String tuseprocess; // 자재소요공정

    @Column(nullable = false)
    private String tusedate; // 자재소요일정

    @Column(nullable = false)
    private String tusecount; // 자재소요량

    @Column(nullable = false)
    private String deliverydate; // 조달납기

}
