package com.sdjzu.sell.converter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sdjzu.sell.dataobject.OrderDetail;
import com.sdjzu.sell.dto.OrderDTO;
import com.sdjzu.sell.enums.ResultEnum;
import com.sdjzu.sell.exception.SellException;
import com.sdjzu.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * orderForm转换成OrderDTO
 * @author lychee
 * @date 2020/1/13 21:03
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

//        基本属性的转换，因为属性名不对应，不能使用BeanUtils.Properties()
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        //把json转成list
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
