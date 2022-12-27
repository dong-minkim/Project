package com.dutmdcjf.backendserver.controller;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.model.*;
import com.dutmdcjf.backendserver.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @NonAuth
    @GetMapping("/history")
    public Object getOrderList(@RequestAttribute(name = "accountIdx") Long accountIdx) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", orderService.getOrderHistoryList(accountIdx));
        return map;
    }

    @NonAuth
    @GetMapping("/invoice")
    public Object getOrderItemList(@RequestAttribute(name = "accountIdx") Long accountIdx,
                                   @RequestBody OrderInvoice orderInvoice) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", orderService.getOrderInvoiceList(accountIdx, orderInvoice));
        map.put("count", 0);
        map.put("page", 0);
        return map;
    }
}
