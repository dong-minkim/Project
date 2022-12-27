package com.dutmdcjf.backendserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class OrderItem {
    Long orderItemsIdx;
    Long accountIdx;
    Long orderIdx;
    Long productIdx;
    BigDecimal price;
    Integer quantity;
    Integer inApack;
    Date orderDate;
    String purId;
    Date date;
    Integer done;
}
