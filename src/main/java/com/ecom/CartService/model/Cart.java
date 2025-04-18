package com.ecom.CartService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Cart {

    @Id
    private ObjectId id;
    private Integer quantity;

    private List<Integer> userIds;

    private  List<String> productIds;

    private String ProductName;

    private String imageName;

    private Double ProductPrice;

   @Transient
    private Double totalPrice;

    @Transient
    private Double totalOrderPrice;

}
