package com.demo.ciValue.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String productId;
    private String category;
    private String brand;
}
