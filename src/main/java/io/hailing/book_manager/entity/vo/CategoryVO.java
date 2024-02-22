package io.hailing.book_manager.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CategoryVO implements Serializable {
    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "顺序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
