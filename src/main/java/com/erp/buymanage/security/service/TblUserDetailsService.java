package com.erp.buymanage.security.service;

//import com.erp.buymanage.dto.PageResultDTO;
import com.erp.buymanage.entity.TblMember;
//import com.erp.buymanage.dto.MemberPageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.erp.buymanage.repository.TblMemberRepository;
import com.erp.buymanage.security.dto.AuthMemberDTO;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class TblUserDetailsService implements UserDetailsService {

    private final TblMemberRepository tblMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ClubUserDetailsService loadUserByUsername " + username);
        Optional<TblMember> result = tblMemberRepository.findByEmail(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check User Email or from Social ");
        }
        TblMember tblMember = result.get();
        log.info("-----------------------------");
        log.info(tblMember);
        AuthMemberDTO authMember = new AuthMemberDTO(
                tblMember.getEmail(),
                tblMember.getPassword(),
                tblMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );
        authMember.setName(tblMember.getName());
        authMember.setFromSocial(tblMember.isFromSocial());
        return authMember;
    }
}