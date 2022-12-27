package com.dutmdcjf.backendserver.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductList {
    BigDecimal discount;
    Integer total;
    Integer page;
    Integer count;
    Integer cate1;
    Integer cate2;

    List<Product> list;
}
