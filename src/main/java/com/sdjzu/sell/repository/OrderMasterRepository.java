package com.sdjzu.sell.repository;

import org.springframework.data.domain.Page;
import com.sdjzu.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lychee
 * @date 2020/1/11 20:08
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    //todo:jpa分页
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);



}
