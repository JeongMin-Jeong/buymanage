package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_orderchart")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;
    private String date;

    private String orderstate;
    private String ocode;
}
