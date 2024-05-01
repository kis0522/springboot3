package com.example.project06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    private Integer id;
    private String title;
    private Integer price;
    private String user;
    private String userdate;
}
