package com.dutmdcjf.backendserver.dao.mapper;

import com.dutmdcjf.backendserver.model.Product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductMapper {
    Integer getCategoryListCount(@Param("cate1") Integer cate1);

    List<Product> getCategoryItemList(@Param("cate1") Integer cate1, @Param("page") Integer page, @Param("count") Integer count);

    Integer getSubCategoryListCount(@Param("cate1") Integer cate1, @Param("cate2") Integer cate2);

    List<Product> getSubCategoryItemList(@Param("cate1") Integer cate1, @Param("cate2") Integer cate2, @Param("page") Integer page, @Param("count") Integer count);

    Integer getProductItemListCount();

    List<Product> getProductItemList(@Param("page") Integer page, @Param("count") Integer count);

    Integer getProductSearchCount(@Param("searchProduct") String searchProduct);

    List<Product> getProductSearchItemList(@Param("searchProduct") String searchProduct, @Param("page") Integer page, @Param("count") Integer count);
}
