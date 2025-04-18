package com.ecom.CartService.DTO;

import com.ecom.CartService.model.Product;
import com.ecom.CartService.model.UserDtls;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest_DTO {
private UserDtls userDtls;
private Product product;
}
