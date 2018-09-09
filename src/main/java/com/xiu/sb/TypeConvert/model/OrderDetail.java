package com.xiu.sb.TypeConvert.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author  xieqx
 * date   2018/9/7
 * 订单详情
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {

    private Long productId;

    private String productName;

    /**
     * 日期类型
     */
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date  buyTime;


}
