package com.example.mobile.entity;

import com.example.mobile.constant.Role;
import com.example.mobile.dto.MobileFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "templates/mobile")
@Getter @Setter
@ToString
public class Mobile {
    @Id
    @Column(name = "mobile_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;
    public static Mobile createMobile(MobileFormDto mobileFormDto){
        Mobile mobile = new Mobile();
        mobile.setName(mobileFormDto.getName());
        mobile.setEmail(mobileFormDto.getEmail());
        mobile.setAddress(mobileFormDto.getAddress());
        //String password = passwordEncoder.encode(mobileFormDto.getPassword());
        //mobile.setPassword(password);
        //mobile.setRole(Role.ADMIN);
        mobile.setRole(Role.USER);

        return mobile;
    }
}
