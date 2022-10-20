package com.erp.buymanage.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TblCategoryChild {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long ccid;
    private Long pcid;
    private String ccname;
}