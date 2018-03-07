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
 * <p>date: 2018-03-06 15:11</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Attachment {
    private Long id;
    private String name;
    private String oldName;
    private Long size;
    private Integer type;
    private String pathPrefix;
    @TableLogic
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
