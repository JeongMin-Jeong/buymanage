package com.erp.buymanage.entity;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.sql.Date;

@Entity(name="tbl_order")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono;

    private String ocode;
    private String orderdate;
    private String deliverydate;

    private String pname;
    private int oquantity;
    private int pprice;
    private String cpartnername;
    private String omanager;
    private String ostate;
    private String oetc;

    private String ptype1;
    private String ptype2;
    private String pcode;
    private String pcontent;

    private Long cno;

    public void changeOrderdate(String orderdate){
        this.orderdate = orderdate;
    }

    public void changeDeliverydate(String deliverydate){
        this.deliverydate = deliverydate;
    }
    public void changeOquantity(int oquantity){
        this.oquantity = oquantity;}  public void changePname(String pname){
        this.pname = pname;
    } public void changePprice(int pprice){
        this.pprice = pprice;
    } public void changeCpartnername(String cpartnername){
        this.cpartnername = cpartnername;
    } public void changeOstate(String ostate){
        this.ostate = ostate;
    }
    public void changeOmanager(String omanager){
        this.omanager = omanager;
    }
    public void changeOetc(String oetc){
        this.oetc = oetc;
    }
    public void changePtype1(String ptype1){
        this.ptype1 = ptype1;
    }public void changePtype2(String ptype2){
        this.ptype2 = ptype2;
    }
    public void changePcode(String pcode){
        this.pcode = pcode;
    }
    public void changePcontent(String pcontent){
        this.pcontent = pcontent;
    }
    public  void changeOcode(String ocode) {this.ocode=ocode;}

}
