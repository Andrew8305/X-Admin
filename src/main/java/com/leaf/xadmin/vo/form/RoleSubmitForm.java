package com.leaf.xadmin.vo.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author leaf
 * <p>date: 2018-03-06 13:41</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleSubmitForm {
    private Long id;
    private String name;
    private String desc;
}
