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
public class SysMenu {

    private String id;
    private String parentId;
    private String name;
    private String path;
    private String perms;
    private String component;
    private boolean type;
    private String icon;
    private String orderNum;
    private Date created;
    private Date updated;
    private Boolean status;
}
