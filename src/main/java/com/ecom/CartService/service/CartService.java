package com.ecom.CartService.service;

import com.ecom.CartService.model.Cart;
import com.ecom.CartService.model.Product;
import com.ecom.CartService.model.UserDtls;

import java.util.List;

public interface CartService {
    public Cart saveCart(Product product, UserDtls user);

    public List<Cart> getCartsByUser(Integer userId);

    public Integer getCountCart(Integer userId);

    public void updateQuantity(String sy, String cid);

}
