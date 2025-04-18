package com.ecom.CartService.service.impl;
import com.ecom.CartService.model.Cart;
import com.ecom.CartService.model.Product;
import com.ecom.CartService.model.UserDtls;
import com.ecom.CartService.repository.CartRepository;
import com.ecom.CartService.service.CartService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
CartRepository cartRepository;


    @Override
    public Cart saveCart(Product product, UserDtls user) {

        Cart cartStatus = cartRepository.findByProductIdsAndUserIds(product.getId(), user.getId());
        Cart cart=null;
        if(cartStatus==null){
            cart=new Cart();
            List<String> productList=new ArrayList();
            productList.add(product.getId());
            cart.setProductIds(productList);
            List<Integer> userList=new ArrayList();
            userList.add(user.getId());
            cart.setUserIds(userList);
            cart.setQuantity(1);
            cart.setTotalPrice(1*product.getDiscountPrice());
            cart.setProductName(product.getTitle());
            cart.setImageName(product.getImage());
            cart.setProductPrice(product.getDiscountPrice());
        }
        else {
         cart =cartStatus;
         cart.setQuantity(cart.getQuantity()+1);
         cart.setTotalPrice(cart.getQuantity()*product.getDiscountPrice());
        }
        Cart save = cartRepository.save(cart);

        return save;
    }

    @Override
    public List<Cart> getCartsByUser(Integer userId)
    {
        List<Cart> carts = cartRepository.findByUserIdsContaining(userId);

        return carts;
    }

    @Override
    public Integer getCountCart(Integer userId) {
        Integer count = cartRepository.countByUserIdsContaining(userId);
        return count;
    }

    @Override
    public void updateQuantity(String sy, String cid) {
        ObjectId id=new ObjectId(cid);
        Cart cart = cartRepository.findById(id).get();
        int updateQuantity;
        if (sy.equalsIgnoreCase("de")) {
            updateQuantity = cart.getQuantity() - 1;

            if (updateQuantity <= 0) {
                cartRepository.delete(cart);
            } else {
                cart.setQuantity(updateQuantity);
                cartRepository.save(cart);
            }

        } else {
            updateQuantity = cart.getQuantity() + 1;
            cart.setQuantity(updateQuantity);
            cartRepository.save(cart);
        }

    }
}
