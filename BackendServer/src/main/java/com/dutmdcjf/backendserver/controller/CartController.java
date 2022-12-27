package com.dutmdcjf.backendserver.controller;

import com.dutmdcjf.backendserver.annotation.NonAuth;
import com.dutmdcjf.backendserver.model.*;
import com.dutmdcjf.backendserver.model.Cart.CartItem;
import com.dutmdcjf.backendserver.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @NonAuth
    @GetMapping("/list")
    public Object getCartList(@RequestAttribute(name = "accountIdx") Long accountIdx) throws Exception {
        return cartService.getList(accountIdx);
    }

    @NonAuth
    @PostMapping("/item")
    public Response insertItem(@RequestAttribute(name = "accountIdx") Long accountIdx, @RequestBody CartItem cartItem) {
        if (cartService.setCartItem(accountIdx, cartItem)) {
            return new Response(200, "success");
        }

        return new Response(500, "fail");
    }

    @GetMapping("/item/{itemIdx}")
    public Response selectItem(@RequestAttribute(name = "accountIdx") Long accountIdx, @PathVariable(value = "itemIdx") Long itemIdx) {
        //CartItem item = cartService.selectItem(accountId, itemId);

        return new Response(200, null);
    }

    @NonAuth
    @PutMapping("/item")
    public Response updateItem(@RequestAttribute(name = "accountIdx") Long accountIdx, @RequestBody CartItem cartItem) {
        if (cartService.updateItem(accountIdx, cartItem)) {
            return new Response(200, "success");
        }

        return new Response(500, "fail");
    }

    @NonAuth
    @DeleteMapping("/item/{itemIdx}")
    public Response deleteItem(@RequestAttribute(name = "accountIdx") Long accountIdx, @PathVariable(name = "itemIdx") Long itemIdx) {
        if (cartService.deleteItem(accountIdx, itemIdx)) {
            return new Response(200, "success");
        }

        return new Response(500, "fail");
    }

    @PutMapping("/checkout")
    public Response orderCheckOut(@RequestAttribute(name = "accountIdx") Long accountIdx, @RequestBody Order order) {
        try{
            //cartService.orderCheckout(accountId, order);
            return new Response(200, "success");
        } catch (Exception e) {
            return new Response(500, "fail");
        }
    }

}
