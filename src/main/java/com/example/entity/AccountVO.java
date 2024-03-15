package com.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AccountVO {
    private String username;
    private String gender;
    private String role;
    //男 1 女 0
    private String genderNum;

    private Integer isDelete;
}
