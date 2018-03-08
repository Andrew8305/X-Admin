package com.leaf.xadmin.vo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leaf.xadmin.utils.serializer.String2SecretSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author leaf
 * <p>date: 2018-02-24 13:10</p>
 * <p>version: 1.0</p>
 */
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoDTO {
    private String id;
    @JsonSerialize(using = String2SecretSerializer.class)
    private String name;
    private String avatar;
}
