package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO implements BaseData {

    private Long id;
    private String username;
    private String password;
    private String gender;
    private String email;
    private String role;
    private Date registerTime;
    private Integer isDelete;
}
