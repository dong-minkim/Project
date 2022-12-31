package com.dutmdcjf.backendserver.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderList {
    List<Order> orders;
}
