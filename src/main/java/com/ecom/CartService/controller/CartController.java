package com.ecom.CartService.controller;

import com.ecom.CartService.DTO.CartRequest_DTO;
import com.ecom.CartService.DTO.Cart_DTO;
import com.ecom.CartService.model.Cart;
import com.ecom.CartService.model.Product;
import com.ecom.CartService.model.UserDtls;
import com.ecom.CartService.service.CartService;
import com.ecom.CartService.service.impl.CartServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api1")
public class CartController {

    @Autowired
CartServiceImpl cartService;

    public static Cart_DTO toCartDTO(Cart cart) {
        Cart_DTO cartDTO = new Cart_DTO();

        // Copy each field
        cartDTO.setId(cart.getId().toString());  // Convert ObjectId to String
        cartDTO.setUserIds(cart.getUserIds());
        cartDTO.setProductIds(cart.getProductIds());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setTotalOrderPrice(cart.getTotalOrderPrice());
        cartDTO.setProductName(cart.getProductName());
        cartDTO.setImageName(cart.getImageName());
        cartDTO.setProductPrice(cart.getProductPrice());

        return cartDTO;
    }


    public static Cart toCart(Cart_DTO cart) {
        Cart cartDTO = new Cart();

        // Copy each field
        ObjectId id=new ObjectId(cart.getId());
        cartDTO.setId(id);  // Convert ObjectId to String
        cartDTO.setUserIds(cart.getUserIds());
        cartDTO.setProductIds(cart.getProductIds());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setTotalOrderPrice(cart.getTotalOrderPrice());
        cartDTO.setProductName(cart.getProductName());
        cartDTO.setImageName(cart.getImageName());
        cartDTO.setProductPrice(cart.getProductPrice());

        return cartDTO;
    }

    @GetMapping("/")
    public  String welcome(){
        return "Hello";
    }
    @PostMapping("/addCart")
    public ResponseEntity<Boolean> addToCart(@RequestBody CartRequest_DTO cartRequestDto) {
        Cart cart=cartService.saveCart(cartRequestDto.getProduct(),cartRequestDto.getUserDtls());
        Cart_DTO cartDto=toCartDTO(cart);

        if (cartDto!=null)
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        else
            return new ResponseEntity<Boolean>(true, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/countProduct")
    public ResponseEntity<Integer> countProducts(@RequestParam Integer userId) {
        // Logic to count products for the given userId
        int productCount = cartService.getCountCart(userId);

        return ResponseEntity.ok(productCount);
    }

    @GetMapping("/getCart")
    public ResponseEntity<List<Cart_DTO>> getCart(@RequestParam Integer userId) {
        // Logic to count products for the given userId
        List<Cart> cartsByUser = cartService.getCartsByUser(userId);
//        System.out.println(cartsByUser.get(0).getQuantity());
            List<Cart_DTO> cartDtoList = cartsByUser.stream().map((Cart cart) -> {
                return toCartDTO(cart);
            }).collect(Collectors.toList());

            return ResponseEntity.ok(cartDtoList);

    }

    @GetMapping("/cartQuantityUpdate")

    public ResponseEntity<Boolean> cartQuantityUpdate(@RequestParam String sy,@RequestParam String cid) {
        cartService.updateQuantity(sy,cid);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);

    }

}
