package com.leaf.xadmin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author leaf
 * <p>date: 2018-01-13 21:24</p>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorTemplateVO {
    private String code;
    private String error;
    private String message;
    private String detail;
    private String exception;
    private String path;
}
