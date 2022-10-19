package com.erp.buymanage.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //패스워드를 압호화 하기 위한 객체를 생성
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll() //모든사용자가 접속가능한 url
                .antMatchers("/sample/member").hasRole("USER") //USER
                .antMatchers("/product/list").hasRole("PRODUCT") //PRODUCT
                .antMatchers("/contract/list").hasRole("CONTRACT") //COTRACT
                .antMatchers("/order/list").hasRole("ORDER") //ORDER
                .antMatchers("/stock/list").hasRole("STOCK") //STOCK
                .antMatchers("/sample/admin").hasRole("ADMIN") //ADMIN
        ;
        http.formLogin();//인증문제 발생시 오류화면 대신 로그인화면 출력
//        http.formLogin()
//                .loginPage("/members/login")
//                .defaultSuccessUrl("/")
//                .usernameParameter("email")
//                .failureUrl("/members/login/error")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
//                .logoutSuccessUrl("/")
//        ;
        http.csrf().disable(); //CSRF토큰 발행금지 해킹방어용
        http.logout(); //로그아웃처리
    }

//    더이상 사용않음..
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //사용자 계정은 user1
//        auth.inMemoryAuthentication().withUser("user1")
//                .password("$2a$10$HQbLKIka89rObQrrsYN/3eD5cv8GeSfcPCOCVxFR2sLI/fQM0NQW.") //1111 인코딩된 결과 - PaasordTests재수행시마다 바뀜...
//                .roles("USER");
//    }

}
