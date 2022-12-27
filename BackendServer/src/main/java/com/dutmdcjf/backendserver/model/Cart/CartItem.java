package com.dutmdcjf.backendserver.model.Cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @JsonProperty("item_id")
    private Long itemId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("inApack")
    private Integer inApack;
}
