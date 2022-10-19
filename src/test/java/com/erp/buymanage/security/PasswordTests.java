package com.erp.buymanage.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String passowrd = "1111";
        String enPw = passwordEncoder.encode(passowrd);
        System.out.println("enPw : " + enPw);
        boolean matchResult = passwordEncoder.matches(passowrd, enPw); //password enPw비교
        System.out.println("matchResult : " + matchResult);
    }

    //1회
    //    enPw : $2a$10$5.E0ET.DY/5NmGoANGmp..7mUuy3dNfayD3YE4LddfunOY/FEv/.q
    //    matchResult : true
    //2회
    //    enPw : $2a$10$MbKQMm4OhkoCgJ6/ZVSxB.Bjd3vmFGjmAoOalGIEEMh12VZ2Ylbf.
    //    matchResult : true
}
