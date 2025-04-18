package com.ecom.CartService.repository;

import com.ecom.CartService.model.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {

    public Cart findByProductIdsAndUserIds(String productId,Integer userId);

//    public Integer countByUserIds(Integer userId);

   public Integer countByUserIdsContaining(Integer userId);
//    public Cart findById(String id);
public List<Cart> findByUserIdsContaining(Integer userId);
}
