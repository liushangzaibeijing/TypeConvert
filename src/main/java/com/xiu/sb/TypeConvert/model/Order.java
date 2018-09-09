package com.xiu.sb.TypeConvert.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * author  xieqx
 * date   2018/9/7
 * 订单信息
 */
public class Order {

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 预定时间 默认情况下的日期类型也无法进行封装 需要添加自定义的预定时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@JsonFormat( timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date bookingTime;

    /**
     * 离店时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkOutTime;

    /**
     * 订单详情列表，controller封装的order对象中如果没有自定义的类型转换，默认情况下无法正确的封装
     */
    private List<OrderDetail> orderDetailList;

    /**
     * 订单详情列表，controller封装的order对象中如果没有自定义的类型转换，默认情况下无法正确的封装
     */
    private List<User> userList;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
