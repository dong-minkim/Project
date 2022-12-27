package com.dutmdcjf.backendserver.service;

import com.dutmdcjf.backendserver.dao.mapper.CartMapper;
import com.dutmdcjf.backendserver.model.Cart.CartItem;
import com.dutmdcjf.backendserver.model.CartList;
import com.dutmdcjf.backendserver.model.Order;
import com.dutmdcjf.backendserver.model.OrderItem;
import com.dutmdcjf.backendserver.model.Product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartMapper cartMapper;

    public int getItemsCount(Long accountIdx) {
        Long orderIdx = cartMapper.getFindOrder(accountIdx);
        if (orderIdx <= 0) {
            return 0;
        }

        return cartMapper.getCartItemsCnt(orderIdx, accountIdx);
    }

    public CartList getList(Long accountIdx) throws Exception {
        BigDecimal totalPrice = new BigDecimal(0L);
        List<Product> productList = null;

        Long orderIdx = cartMapper.getFindOrder(accountIdx);
        if (orderIdx != null && orderIdx > 0) {
            // 장바구니에 데이터가 없는 것
            productList = cartMapper.getCartItemList(orderIdx, accountIdx);
            totalPrice = cartMapper.getCartTotalPrice(orderIdx, accountIdx);
        }

        return new CartList(orderIdx, totalPrice, productList);
    }

    public Object selectItem(Long itemIdx, Long accountIdx) {
        Order order = cartMapper.getLastOrder(accountIdx);
        if (order != null) {
            //cartMapper.getOrderItem(itemIdx, order.getOrderIdx());
        }

        return null;
    }

    public boolean updateItem(Long accountIdx, CartItem cartItem) {
        int ret = cartMapper.updateCartItem(accountIdx, cartItem.getItemId(), cartItem.getQuantity());
        if (ret != -1) {
            return true;
        }

        return false;
    }

    public boolean deleteItem(Long accountIdx, Long itemIdx) {
        int ret = cartMapper.deleteItem(accountIdx, itemIdx);
        if (ret > 0) {
            return true;
        }

        return false;
    }

    public boolean setCartItem(Long accountIdx, CartItem cartItem) {
        int ret = 0;

        Order order = cartMapper.getLastOrder(accountIdx);
        if (order == null) {
            Long orderId = cartMapper.createCart(accountIdx);
            if (orderId == null || orderId <= 0) {
                return false;
            }

            ret = cartMapper.createOrderItem(accountIdx, orderId, cartItem.getItemId(), cartItem.getPrice(), cartItem.getQuantity(), cartItem.getInApack());
            if (ret <= 0) {
                // 업데이트 실패
                return false;
            }
        } else {
            OrderItem orderItem = cartMapper.getOrderItem(order.getOrderIdx(), cartItem.getItemId());
            if (orderItem != null) {
                ret = cartMapper.updateCartItem(accountIdx, cartItem.getItemId(), cartItem.getQuantity());
                if (ret <= 0) {
                    // 업데이트 실패
                    return false;
                }
            } else {
                ret = cartMapper.createOrderItem(accountIdx, order.getOrderIdx(), cartItem.getItemId(), cartItem.getPrice(), cartItem.getQuantity(), cartItem.getInApack());
                if (ret <= 0) {
                    // 업데이트 실패
                    return false;
                }
            }
        }

        ret = cartMapper.updateCartItem(accountIdx, cartItem.getItemId(), cartItem.getQuantity());
        if (ret != -1) {
            return true;
        }

        return false;
    }
}
