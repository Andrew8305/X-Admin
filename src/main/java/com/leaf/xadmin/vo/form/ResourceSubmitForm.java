package com.leaf.xadmin.vo.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author leaf
 * <p>date: 2018-03-06 14:05</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceSubmitForm {
    private Long id;
    private String name;
    private String desc;
    private String path;
    private Integer type;
}
