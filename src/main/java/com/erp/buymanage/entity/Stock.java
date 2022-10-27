package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_stock")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;


    @Column(nullable = false)
    private String scode;

    @Column(nullable = false)
    private String sname;

    @Column(nullable = false)
    private String scate1;

    @Column(nullable = false)
    private String scate2;

    private int sin;

    private int sout;

    private int sreturn;

    private int stock;

    private String requester;


    private String snote;

    public void changeCode(String scode) {
        this.scode = scode;
    }
    public void changeName(String sname) { this.sname = sname; }
    public void changeCate1(String scate1) { this.scate1 = scate1; }
    public void changeCate2(String scate2) { this.scate2 = scate2; }
    public void changeIn(int sin) { this.sin += sin; }
    public void changeOut(int sout) { this.sout += sout; }
    public void changeReturn(int sreturn) { this.sreturn = sreturn; }
    public void changeStock1(int sin) { this.stock = +sin; }
    public void changeStock2(int sout) { this.stock -= sout; }
    public void changeNote(String snote) { this.snote = snote; }

}
