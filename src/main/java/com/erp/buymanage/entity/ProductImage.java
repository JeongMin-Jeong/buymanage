package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "product") //연관관계에서 사용된 객체는 제외
@Table(name= "tbl_productImg")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String uuid;
    private String imgName;
    private String path ;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
