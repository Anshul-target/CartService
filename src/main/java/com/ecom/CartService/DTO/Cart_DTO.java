package com.ecom.CartService.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;





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

    public class Cart_DTO {



        private String id;

    private List<Integer> userIds;

//    @DBRef
        private List<String> productIds;

        private Integer quantity;


        private Double totalPrice;
    private String ProductName;

    private String imageName;

    private Double ProductPrice;

        private Double totalOrderPrice;



}
