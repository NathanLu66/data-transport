package com.data.transport.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String uid;
    private String username;
    private String password;
    private String salt;
    private String role;
    private Integer isDelete;
    private String createdBy;
    private Date createdTime;
    private String modifiedBy;
    private Date modifiedTime;
}