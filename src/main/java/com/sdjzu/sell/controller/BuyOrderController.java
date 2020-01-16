package com.sdjzu.sell.controller;

import com.sdjzu.sell.VO.ResultVO;
import com.sdjzu.sell.converter.OrderForm2OrderDTOConverter;
import com.sdjzu.sell.dto.OrderDTO;
import com.sdjzu.sell.enums.ResultEnum;
import com.sdjzu.sell.exception.SellException;
import com.sdjzu.sell.form.OrderForm;
import com.sdjzu.sell.service.BuyerService;
import com.sdjzu.sell.service.OrderService;
import com.sdjzu.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lychee
 * @date 2020/1/13 20:45
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyOrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private BuyerService buyerService;

    //创建订单
    //todo:集成swagger
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@RequestBody OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);

        orderDTOPage.getTotalElements();//返回的总数
        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //todo:不安全的做法，待改进
//        OrderDTO orderDTO=orderService.findById(openid);
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
//        todo：不安全的做法，改进
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
