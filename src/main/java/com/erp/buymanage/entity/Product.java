package com.erp.buymanage.entity;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name= "tbl_product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    @Column(length = 50, nullable = false)
    private String pcode; // 품목코드

    @Column(length = 255, nullable = false)
    private String pname; // 품목이름

    @Column(length = 50, nullable = false)
    private String ptype1; // 대분류

    @Column(length = 50, nullable = false)
    private String ptype2; // 중분류

    @Column(length = 255, nullable = false)
    private String pcontent; // 설명

    @Column(length = 255, nullable = false)
    private String petc; // 비고

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ProductImage> productImages = new HashSet<>();

    public void changePname(String pname){
        this.pname = pname;
    }
    public void changePtype1(String ptype1){
        this.ptype1 = ptype1;
    }
    public void changePtype2(String ptype2){
        this.ptype2 = ptype2;
    }
    public void changePcontent(String pcontent) { this.pcontent = pcontent; }
    public void changePetc(String petc) { this.petc = petc; }

}
