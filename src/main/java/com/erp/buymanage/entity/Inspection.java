package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity(name="tbl_inspection")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;
    private Long ono_fk;
    private String inspection_date1;
    private int inspection_degree1;
    private String inspection_prime1;
    private String inspection_etc1;
    private String inspection_date2;
    private int inspection_degree2;
    private String inspection_prime2;
    private String inspection_etc2;
}
