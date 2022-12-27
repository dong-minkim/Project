package com.dutmdcjf.backendserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderList {
    List<Order> orders;
}
