package com.leaf.xadmin.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author leaf
 * <p>date: 2017-12-28 21:35</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String id;
    private String phone;
    private String name;
    private String email;
    private String pass;
    private Integer status;
    private Integer type;
    private Date lastLoginTime;
    private String lastLoginIp;
    private String lastLoginLocal;
    @TableLogic
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
