package com.xiu.sb.TypeConvert.web;

import com.xiu.sb.TypeConvert.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by xieqx on 2018/7/26
 * @author  xieqx
 * @desc  去呼呼调用的订单接口
 */
@Controller
@RequestMapping("/api")
public class DateConvertController {
    private static final Logger logger = LoggerFactory.getLogger(DateConvertController.class);


    /**
     *
     * @param order 订单信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "test",method = RequestMethod.POST,consumes = "application/x-www-form-urlencoded",produces = "application/json")
    public Object checkInventoryForm(Order order){
        if(order==null){
            throw new RuntimeException("the hotelOrder is null");
        }
        return order;
    }



}
