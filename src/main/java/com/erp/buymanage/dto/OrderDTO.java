package com.erp.buymanage.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Data
public class OrderDTO

{
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

    private String inspectionstate;

//    @QueryProjection
//    public OrderDTO(int ono, String orderdate, String deliverydate, String opname, int oquantity, int oprice, String ocompany, String omanager,
//                    String ostate,String oetc) {
//        this.ono = ono;
//        this.orderdate = orderdate;
//        this.deliverydate = deliverydate;
//        this.opname = opname;
//        this.oquantity = oquantity;
//        this.oprice = oprice;
//        this.ocompany = ocompany;
//        this.omanager = omanager;
//        this.ostate = ostate;
//        this.oetc = oetc;
//    }
//@Entity(name = "test_jojo")
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Jojo {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "jo_id")
//    private Long joId;
//
//    @Column
//    private LocalDateTime regDate;
//
//}

}
