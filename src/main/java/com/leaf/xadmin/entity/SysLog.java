package com.leaf.xadmin.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author leaf
 * <p>date: 2018-01-12 17:14</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SysLog {
    private Long id;
    private String local;
    private String desc;
    private Integer type;
    private Integer status;
    @TableLogic
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
