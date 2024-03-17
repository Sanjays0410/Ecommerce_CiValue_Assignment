package com.demo.ciValue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String ProductId;
    @Column
    private String category;
    @Column
    private String brand;


}


