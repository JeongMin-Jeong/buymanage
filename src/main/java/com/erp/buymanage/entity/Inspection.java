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
    private LocalDateTime inspectionDate1;
    private int inspectionOfdegree1;
    private LocalDateTime inspectionDate2;
    private int inspectionOfdegree2;
}
