package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "stock")
public class History extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hno;

    private String text;

    private String requester;

    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;
}
