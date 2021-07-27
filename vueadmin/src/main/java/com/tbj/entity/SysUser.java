package com.tbj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysUser {

    private String id;
    private String username;
    private String password;
    private String avatar;
    private String email;
    private String city;
    private Date created;
    private Date updated;
    private Date lastLogin;
    private boolean status;
}
