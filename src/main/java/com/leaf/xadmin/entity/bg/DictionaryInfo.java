package com.leaf.xadmin.entity.bg;

import com.baomidou.mybatisplus.annotations.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author leaf
 * <p>date: 2018-03-06 14:21</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictionaryInfo {
    private String id;
    private Integer code;
    private String name;
    private Integer rank;
    private String desc;
    private String dictionaryType;
    @TableLogic
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
