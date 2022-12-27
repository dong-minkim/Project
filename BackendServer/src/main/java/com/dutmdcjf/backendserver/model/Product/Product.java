package com.dutmdcjf.backendserver.model.Product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long idx;
    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("products_id")
    private Long productsId;
    @JsonProperty("products_price")
    private BigDecimal productsPrice;
    @JsonProperty("products_quantity")
    private Integer productsQuantity;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("retail_price")
    private BigDecimal retailPrice;
    @JsonProperty("cat_code")
    private String catCode;
    private Integer inApack;
    @JsonProperty("products_disc")
    private BigDecimal productsDisc;
    @JsonProperty("buy_count")
    private Integer buyCount;
    private Integer cate1;
    private Integer cate2;
    private Integer cate3;
    @JsonProperty("products_name")
    private String productsName;
    @JsonProperty("products_description")
    private String productsDescription;
    @JsonProperty("order_date")
    private Date orderDate;
}
