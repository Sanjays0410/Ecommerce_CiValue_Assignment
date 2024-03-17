package com.demo.ciValue.controller;

import com.demo.ciValue.entity.Product;
import com.demo.ciValue.dto.ShopperRequest;
import com.demo.ciValue.service.ShelvesService;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.naming.LimitExceededException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.demo.ciValue.util.AppConstant.INSERTION_SUCCESS;

@RestController
@Validated
public class ShopperController {

    @Autowired
    ShelvesService shelvesService;

    @PostMapping(value="/saveShopper",produces = {"application/json"},consumes = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity insertDate(@RequestBody List<ShopperRequest> ShelvesRequestList) throws IOException {

        shelvesService.addShopperDetails(ShelvesRequestList);
        return new ResponseEntity<>(INSERTION_SUCCESS,HttpStatus.CREATED);
    }

    @GetMapping("/productsByProductId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProductByProductId(@RequestParam (value="productId", required=true)  String productId, @RequestParam(value="limit" ,required=false,defaultValue="10") @Max(value=1000, message = "should be less than or equal to 1000")Integer limit) throws LimitExceededException {

        Optional<List<Product>> product = shelvesService.getProductByProductId(productId,limit);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);

    }

}
