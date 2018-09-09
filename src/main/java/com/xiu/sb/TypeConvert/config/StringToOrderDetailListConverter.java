package com.xiu.sb.TypeConvert.config;

import com.xiu.sb.TypeConvert.model.OrderDetail;
import com.xiu.sb.TypeConvert.utils.JsonUtil;
import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author  xieqx
 * date   2018/9/4
 * 将String 转换为list集合
 */
public class StringToOrderDetailListConverter extends StringToListConverter<OrderDetail> {

}
