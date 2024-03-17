package com.demo.ciValue.repository;

import com.demo.ciValue.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    @Override
     <S extends Product> List<S> saveAll(Iterable<S> entities);

    @Query(nativeQuery = true, value = "SELECT * FROM product where category=:category limit :limit")
    Optional<List<Product>> findByCategory(@Param("category") String category, @Param("limit")int limit);


    @Query(nativeQuery = true, value = "SELECT * FROM product where brand=:brand limit :limit")
    Optional<List<Product>> findByBrand(@Param("brand") String brand, @Param("limit")int limit);


    @Query(nativeQuery = true, value = "SELECT * FROM product where shopperId=:shopperId limit :limit")
    Optional<List<Product>> findByShopperId(@Param("shopperId") String shopperId, @Param("limit")int limit);

    @Query(nativeQuery = true, value = "SELECT * FROM product where product_id=:product_id limit :limit")
    Optional<List<Product>> findByProductId(@Param("product_id") String productId, @Param("limit")int limit);



}
