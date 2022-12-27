package com.dutmdcjf.backendserver.dao.mapper;

import com.dutmdcjf.backendserver.model.Invoice;
import com.dutmdcjf.backendserver.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    List<Order> getOrderHistory(@Param("accountIdx") Long accountIdx);

    List<Invoice> getOrderInvoiceList(@Param("accountIdx") Long accountIdx, @Param("status") Integer status, @Param("startDate") String startDate, @Param("endDate") String endDate);

    List<Invoice> getOrderInvoiceList(@Param("accountIdx") Long accountIdx, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
