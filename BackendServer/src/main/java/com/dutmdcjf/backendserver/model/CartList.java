package com.dutmdcjf.backendserver.model;

import com.dutmdcjf.backendserver.model.Product.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class CartList {
    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("total_price")
    private BigDecimal totalPrice;
    @JsonProperty("list")
    private List<Product> list;
}
