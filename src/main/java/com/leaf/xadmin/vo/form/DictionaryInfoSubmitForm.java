package com.leaf.xadmin.vo.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author leaf
 * <p>date: 2018-03-06 20:20</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictionaryInfoSubmitForm {
    private Long id;
    private Integer code;
    private String name;
    private Integer status;
    private Integer asc;
    private String desc;
    private Long dictionaryType;
}
