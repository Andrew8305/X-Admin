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
 * <p>date: 2018-01-25 21:46</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resource {
    private String id;
    private String parentId;
    private String name;
    private String desc;
    private String path;
    private Integer type;
    @TableLogic
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
