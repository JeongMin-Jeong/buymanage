package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_stockchart")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    private String date;

    private int amount;

    private String scode;
}
