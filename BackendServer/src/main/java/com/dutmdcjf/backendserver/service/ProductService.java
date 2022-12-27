package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.dao.mapper.ProductMapper;
import com.dutmdcjf.backendserver.model.Product.Product;
import com.dutmdcjf.backendserver.model.Product.ProductList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;

    public ProductList getSearch(Integer page, Integer count, String search) {
        List<Product> list = null;
        Integer totalCount = 0;

        totalCount = productMapper.getProductSearchCount(search);
        list = productMapper.getProductSearchItemList(search, page, count);

        return new ProductList(new BigDecimal(0.0), totalCount, (page + 1), count, 0, 0, list);
    }

    public ProductList getList(Integer page, Integer count, Integer cate1, Integer cate2) {
        List<Product> list = null;
        Integer totalCount = 0;

        if (cate1 > 0 && cate2 <= 0) {
            totalCount = productMapper.getCategoryListCount(cate1);
            list = productMapper.getCategoryItemList(cate1, page, count);
        } else if (cate1 > 0 && cate2 > 0) {
            totalCount = productMapper.getSubCategoryListCount(cate1, cate2);
            list = productMapper.getSubCategoryItemList(cate1, cate2, page, count);
        } else {
            totalCount = productMapper.getProductItemListCount();
            list = productMapper.getProductItemList(page, count);
        }

        return new ProductList(new BigDecimal(0.0), totalCount, (page + 1), count, cate1, cate2, list);
    }
}
