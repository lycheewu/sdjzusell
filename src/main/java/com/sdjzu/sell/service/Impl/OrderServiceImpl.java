package com.sdjzu.sell.service.Impl;

import com.sdjzu.sell.dataobject.OrderDetail;
import com.sdjzu.sell.dataobject.OrderMaster;
import com.sdjzu.sell.dataobject.ProductInfo;
import com.sdjzu.sell.dto.CartDTO;
import com.sdjzu.sell.dto.OrderDTO;
import com.sdjzu.sell.enums.OrderStatusEnum;
import com.sdjzu.sell.enums.PayStatusEnum;
import com.sdjzu.sell.enums.ResultEnum;
import com.sdjzu.sell.exception.SellException;
import com.sdjzu.sell.repository.OrderDetailRepository;
import com.sdjzu.sell.repository.OrderMasterRepository;
import com.sdjzu.sell.service.OrderService;
import com.sdjzu.sell.service.ProductService;
import com.sdjzu.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lychee
 * @date 2020/1/11 21:09
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMasterRepository orderMasterRepo;

    @Resource
    private OrderDetailRepository orderDetailRepo;

    @Resource
    private ProductService productService;

    @Override
    @Transactional //添加事务，发生错误时数据库回滚
    public OrderDTO create(OrderDTO orderDTO) {

        //随机生成主键
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        //1. 查询商品（数量，价格）
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
//            if(productInfo==null)
            if (Objects.isNull(productInfo)) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算总价  multiply乘法
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepo.save(orderDetail);
            //不推荐这种写 扣库存
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        //3. 写入订单数据库（orderMaster和orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);

        //注意：这个copy会将orderDTO内的所有数据copy到后一个DO内，会有数据覆盖
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepo.save(orderMaster);

        //4. 扣(减)库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findById(String orderId) {

        OrderMaster orderMaster = orderMasterRepo.findById(orderId).orElse(null);
        if (Objects.isNull(orderMaster)) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepo.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
