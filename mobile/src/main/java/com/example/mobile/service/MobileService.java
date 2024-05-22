package com.example.mobile.service;

import com.example.mobile.entity.Mobile;
import com.example.mobile.repository.MobileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MobileService implements UserDetailsService {
    private final MobileRepository mobileRepository;
    public Mobile saveMobile(Mobile mobile){
        validateDuplicateMobile(mobile);
        return mobileRepository.save(mobile);
    }
    public void validateDuplicateMobile(Mobile mobile){
        Mobile findMobile = mobileRepository.findByEmail(mobile.getEmail());
        if(findMobile != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    String name;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Mobile mobile = mobileRepository.findByEmail(email);
        name = mobile.getName();

        if(mobile == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(mobile.getEmail())
                .password(mobile.getPassword())
                .roles(mobile.getRole().toString())
                .build();
    }
    public String getNameByEmail(String email){
        Mobile mobile = mobileRepository.findByEmailByNative(email);
        return mobile.getName();
    }
    public Long getIdByEmail(String email){
        Mobile mobile = mobileRepository.findByEmailByNative(email);
        return mobile.getId();
    }

    public String getAddressByEmail(String email){
        Mobile mobile = mobileRepository.findByEmailByNative(email);
        return mobile.getAddress();
    }

    public void deleteByEmail(String email){
        Mobile mobile = mobileRepository.findByEmailByNative(email);
        mobileRepository.deleteById(mobile.getId());
    }

}
