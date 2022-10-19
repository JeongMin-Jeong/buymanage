package com.erp.buymanage.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TblCategory {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long cid;
    private String cname;
}