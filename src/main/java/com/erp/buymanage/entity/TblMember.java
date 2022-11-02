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
public class TblMember extends BaseEntity {

    @Id
    private String email;
    private String password;
    private String name;
    private boolean fromSocial; //소셜 로그인 가능여부

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long mno;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<TblMemberRole> roleSet = new HashSet<>();
    public void addMemberRole(TblMemberRole tblMemberRole){
        roleSet.add(tblMemberRole);
    }
    //    Hibernate:
    //
    //    create table club_member (
    //            email varchar(255) not null,
    //    from_social varchar(255),
    //    name varchar(255),
    //    password varchar(255),
    //    primary key (email)
    //    ) engine=InnoDB
    //    Hibernate:
    //
    //    create table club_member_role_set (
    //            club_member_email varchar(255) not null,
    //    role_set integer
    //    ) engine=InnoDB
    //    Hibernate:
    //
    //    alter table club_member_role_set
    //    add constraint FKbfljk8ybtolixxc88osbodrek
    //    foreign key (club_member_email)
    //    references club_member (email)
}

