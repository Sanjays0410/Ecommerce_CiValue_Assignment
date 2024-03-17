package com.demo.ciValue.repository;

import com.demo.ciValue.entity.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShopperDAO extends JpaRepository<Shopper, Long> {

    @Override
    <S extends Shopper> List<S> saveAll(Iterable<S> entities);

    @Query(nativeQuery = true, value = "SELECT * FROM shopper where shopper.shopper_id=:shopperId and shopper.product_id=:productId")
    Optional<Shopper> findByShopperIdAndProductId(@Param("shopperId") String shopperId, @Param("productId") String productId);
}
