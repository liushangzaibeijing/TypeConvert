package com.xiu.sb.TypeConvert;

import com.xiu.sb.TypeConvert.config.StringToListConverter;
import com.xiu.sb.TypeConvert.model.Order;
import com.xiu.sb.TypeConvert.model.OrderDetail;
import com.xiu.sb.TypeConvert.utils.HttpClientUtil;
import com.xiu.sb.TypeConvert.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author  Administrator
 * date   2018/9/7
 */
@Slf4j
public class JsonStrTest {


    @Test
    public void testOrder(){
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderNo("123456");
        order.setBookingTime(new Date());

        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId(1L);
        orderDetail.setBuyTime(new Date());
        orderDetail.setProductName("书籍");

        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductId(2L);
        orderDetail2.setBuyTime(new Date());
        orderDetail2.setProductName("电器");

        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail2);

        order.setOrderDetailList(orderDetails);

        log.info("order info : {}", JsonUtil.obj2Str(order));

        HttpClientUtil httpClientUtil = new HttpClientUtil();
        httpClientUtil.httpPostUrlEncoded("aaa",order);
    }


    @Test
    public void testT(){
      StringToListConverter<OrderDetail> orderDetail =   new StringToListConverter<OrderDetail>();
      //System.out.println(getClass());
      //System.out.println(getClass().getGenericSuperclass());
      Type type  =  ((ParameterizedType) orderDetail.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
      Class clazz = type.getClass();

      System.out.println(clazz);

    }
}
