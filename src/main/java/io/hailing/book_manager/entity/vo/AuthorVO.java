package io.hailing.book_manager.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorVO implements Serializable {
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "时代")
    private String age;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "国家")
    private String country;
}
