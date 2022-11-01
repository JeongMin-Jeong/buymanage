package com.erp.buymanage.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="tbl_inspection")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;
    private String inspection_date1;
    private int inspection_degree1;
    private String inspection_prime1;
    private String inspection_etc1;
    private String inspection_date2;
    private int inspection_degree2;
    private String inspection_prime2;
    private String inspection_etc2;
    //@ManyToOne
    private Long order_ono;
}
