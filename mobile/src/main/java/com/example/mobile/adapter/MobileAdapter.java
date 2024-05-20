package com.example.mobile.adapter;

import com.example.mobile.entity.Mobile;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class MobileAdapter extends User {
    private Mobile mobile;

    public MobileAdapter(Mobile mobile) {
        super(mobile.getEmail(), mobile.getPassword(), List.of(new SimpleGrantedAuthority(mobile.getEmail())));

        this.mobile = mobile;
    }

}
