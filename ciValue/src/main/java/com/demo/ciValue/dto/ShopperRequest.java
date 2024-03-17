package com.demo.ciValue.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShopperRequest {

    private String shopperId;

    private List<Shelf> shelf;
}

