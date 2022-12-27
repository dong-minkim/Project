package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.dao.mapper.OrderMapper;
import com.dutmdcjf.backendserver.model.Invoice;
import com.dutmdcjf.backendserver.model.Order;
import com.dutmdcjf.backendserver.model.OrderInvoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;

    public List<Order> getOrderHistoryList(Long accountIdx) {
        return orderMapper.getOrderHistory(accountIdx);
    }

    public List<Invoice> getOrderInvoiceList(Long accountIdx, OrderInvoice orderInvoice) {
        if (orderInvoice.getInvoiceType() != 99) {
            return orderMapper.getOrderInvoiceList(accountIdx, orderInvoice.getInvoiceType(), orderInvoice.getStartDate(), orderInvoice.getEndDate());
        }

        return orderMapper.getOrderInvoiceList(accountIdx, orderInvoice.getStartDate(), orderInvoice.getEndDate());
    }

}
