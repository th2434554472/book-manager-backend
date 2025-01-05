package io.hailing.book_manager.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReceiptVO {

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "店铺id")
    private Integer storeId;

    @ApiModelProperty(value = "店铺")
    private String store;

    @ApiModelProperty(value = "日期")
    private LocalDate date;

    @ApiModelProperty(value = "支付方式id")
    private Integer paymentId;

    @ApiModelProperty(value = "支付方式")
    private String payment;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
