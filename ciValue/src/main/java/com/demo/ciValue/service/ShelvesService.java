package com.demo.ciValue.service;

import com.demo.ciValue.entity.Product;
import com.demo.ciValue.entity.Shopper;
import com.demo.ciValue.exception.ciValueException;
import com.demo.ciValue.dto.Shelf;
import com.demo.ciValue.dto.ShopperRequest;
import com.demo.ciValue.repository.ProductDAO;
import com.demo.ciValue.repository.ShopperDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.demo.ciValue.util.AppConstant.*;

@Service
public class ShelvesService {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    ShopperDAO shopperDAO;

    public void addShopperDetails(List<ShopperRequest> request) {
        List<Shopper> saveList = new ArrayList<>();
        for(ShopperRequest shopperRequest:request) {
            for (Shelf obj : shopperRequest.getShelf()) {
                Optional<List<Product>> pDto = productDAO.findByProductId(obj.getProductId(), DEFAULT_LIMIT);
                if (!pDto.isPresent())
                    throw new ciValueException("Product not exists " + obj.getProductId());
                Optional<Shopper> shop = shopperDAO.findByShopperIdAndProductId(shopperRequest.getShopperId(), obj.getProductId());
                 if (shop.isPresent())
                    throw new ciValueException("Shopper : " + shopperRequest.getShopperId() + " and Product :" + obj.getProductId() + " combination already exists!");
                else {
                    saveList.add(Shopper.builder().
                            productId(obj.getProductId()).shopperId(shopperRequest.getShopperId()).relevencyScore(obj.getRelevancyScore()).build());
                }
            }
        }
        shopperDAO.saveAll(saveList);

    }
    public Optional<List<Product>> getProductByProductId(String productId, int limit)  {

        Optional<List<Product>> productIdBasedData= productDAO.findByProductId(productId,limit);
        if(productIdBasedData.isPresent()&& (!productIdBasedData.get().isEmpty())){
            return  productIdBasedData;
        }
        else {
            throw new ciValueException(DATA_NOT_FOUND+productId+PRODUCT_ID);
        }
    }

}
