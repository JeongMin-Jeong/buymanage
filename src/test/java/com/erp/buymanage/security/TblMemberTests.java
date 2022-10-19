package com.erp.buymanage.security;

import com.erp.buymanage.entity.TblMember;
import com.erp.buymanage.entity.TblMemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.erp.buymanage.repository.TblMemberRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class TblMemberTests {

    @Autowired
    private TblMemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        //1 - 80까지는 USER만 지정
        //81- 90까지는 USER,MANAGER
        //91- 100까지는 USER,MANAGER,ADMIN
        IntStream.rangeClosed(1,100).forEach(i -> {
            TblMember tblMember = TblMember.builder()
                    .email("user"+i+"@zerock.org")
                    .name("사용자"+i)
                    .fromSocial(false)
                    .roleSet(new HashSet<TblMemberRole>())
                    .password(  passwordEncoder.encode("1111") )
                    .build();
            //default role
            tblMember.addMemberRole(TblMemberRole.USER);
            if(i == 96){
                tblMember.addMemberRole(TblMemberRole.PRODUCT);
                tblMember.addMemberRole(TblMemberRole.MANAGER);
            }
            if(i == 97){
                tblMember.addMemberRole(TblMemberRole.CONTRACT);
                tblMember.addMemberRole(TblMemberRole.MANAGER);
            }
            if(i == 98){
                tblMember.addMemberRole(TblMemberRole.ORDER);
                tblMember.addMemberRole(TblMemberRole.MANAGER);
            }
            if(i == 99){
                tblMember.addMemberRole(TblMemberRole.STOCK);
                tblMember.addMemberRole(TblMemberRole.MANAGER);
            }
            if(i == 100){
                tblMember.addMemberRole(TblMemberRole.PRODUCT);
                tblMember.addMemberRole(TblMemberRole.CONTRACT);
                tblMember.addMemberRole(TblMemberRole.ORDER);
                tblMember.addMemberRole(TblMemberRole.STOCK);
                tblMember.addMemberRole(TblMemberRole.MANAGER);
                tblMember.addMemberRole(TblMemberRole.ADMIN);
            }
            repository.save(tblMember);
        });
    }

    @Test
    public void testRead(){
        Optional<TblMember> result = repository.findByEmail("user95@zerock.org");
        TblMember clubMember = result.get();
        System.out.println(clubMember);
    }
    //    Hibernate:
    //    select
    //    clubmember0_.email as email1_0_,
    //    clubmember0_.from_social as from_soc2_0_,
    //    clubmember0_.name as name3_0_,
    //    clubmember0_.password as password4_0_,
    //    roleset1_.club_member_email as club_mem1_1_0__,
    //    roleset1_.role_set as role_set2_1_0__
    //            from
    //    club_member clubmember0_
    //    left outer join
    //    club_member_role_set roleset1_
    //    on clubmember0_.email=roleset1_.club_member_email
    //            where
    //    clubmember0_.from_social=?
    //    and clubmember0_.email=?
    //    ClubMember(email=user95@zerock.org, password=$2a$10$cPIg99dPgeZvQdO.fSjTS.9kO9K1hE44RMrWVuAVErHE1Ivs5TJ9e, name=사용자95, fromSocial=false, roleSet=[MANAGER, USER, ADMIN])

}
