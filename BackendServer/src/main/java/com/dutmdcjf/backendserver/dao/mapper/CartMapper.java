package com.dutmdcjf.backendserver.dao.mapper;

import com.dutmdcjf.backendserver.model.Order;
import com.dutmdcjf.backendserver.model.OrderItem;
import com.dutmdcjf.backendserver.model.Product.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Mapper
public interface CartMapper {
    Long getFindOrder(@Param("accountIdx") Long accountId);

    Integer getCartItemsCnt(@Param("orderIdx") Long orderIdx, @Param("accountIdx") Long accountIdx);

    BigDecimal getCartTotalPrice(@Param("orderIdx") Long orderIdx, @Param("accountIdx") Long accountIdx);

    List<Product> getCartItemList(@Param("orderIdx") Long orderIdx, @Param("accountIdx") Long accountIdx);

    Order getLastOrder(@Param("accountIdx") Long accountIdx);

    OrderItem getOrderItem(@Param("productIdx") Long productIdx, @Param("orderIdx") Long orderIdx);

    Long createCart(@Param("accountIdx") Long accountIdx);

    Integer createOrderItem(@Param("accountIdx") Long accountIdx,
                            @Param("orderId") Long orderId,
                            @Param("itemId") Long itemId,
                            @Param("price") BigDecimal price,
                            @Param("quantity") Integer quantity,
                            @Param("inApack") Integer inApack);

    Integer setCartItem(@Param("accountIdx") Long accountIdx, @Param("itemId") Long itemId, @Param("quantity") Integer quantity);

    Integer updateCartItem(@Param("accountIdx") Long accountIdx, @Param("itemId") Long itemId, @Param("quantity") Integer quantity);

    Integer deleteItem(@Param("accountIdx") Long accountIdx, @Param("itemId") Long itemId);
}
