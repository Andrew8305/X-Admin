package com.leaf.xadmin.vo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author leaf
 * <p>date: 2018-02-24 12:26</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminInfoDTO {
    private String id;
    private String name;
    private String avatar;
}
