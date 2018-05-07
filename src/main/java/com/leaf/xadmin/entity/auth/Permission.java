package com.leaf.xadmin.entity.auth;

import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author leaf
 * <p>date: 2018-01-03 13:29</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permission {
    private Long id;
    private String name;
    private String desc;
    private Integer type;
    @TableLogic
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
