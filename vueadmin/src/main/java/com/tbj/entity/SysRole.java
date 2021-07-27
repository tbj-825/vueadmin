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
public class SysRole {

    private String id;
    private String name;
    private String code;
    private String remark;
    private Date created;
    private Date updated;
    private boolean status;
}
