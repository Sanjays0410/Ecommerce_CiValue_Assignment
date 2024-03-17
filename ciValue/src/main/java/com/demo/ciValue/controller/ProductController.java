package com.demo.ciValue.controller;

import com.demo.ciValue.dto.ProductRequest;
import com.demo.ciValue.entity.Product;
import com.demo.ciValue.exception.ciValueException;
import com.demo.ciValue.service.ProductService;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class ProductController {

@Autowired
private ProductService productService;



    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/addProduct")
    public ResponseEntity<String> addProducts(@RequestBody List<ProductRequest> request) {
        productService.addProduct(request);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }


    @GetMapping("/productsByCategory")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam(value="category", required=true) String category,@RequestParam(value="limit", required=false,defaultValue="10") @Max(value=100, message = "should be less than or equal to 100") Integer limit) throws ciValueException {

        Optional<List<Product>> product = productService.getProductByCategory(category,limit);
            return new ResponseEntity<>(product.get(), HttpStatus.OK);

    }

    @GetMapping("/productsByBrand")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProductByBrand(@RequestParam (value="brand", required=true)  String brand,@RequestParam(value="limit" ,required=false,defaultValue="10") @Max(value=100, message = "should be less than or equal to 100")Integer limit) {

        Optional<List<Product>> product = productService.getProductByBrand(brand,limit);
            return new ResponseEntity<>(product.get(), HttpStatus.OK);

    }

    @GetMapping("/productsByShopperId")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProductByShopperId(@RequestParam (value="shopperId", required=true)  String shooperId,@RequestParam(value="limit" ,required=false,defaultValue="10") @Max(value=100, message = "should be less than or equal to 100")Integer limit)  {

        Optional<List<Product>> product = productService.getProductByShopperId(shooperId,limit);
            return new ResponseEntity<>(product.get(), HttpStatus.OK);

    }

}
