package com.demo.ciValue.service;


import com.demo.ciValue.dto.ProductRequest;
import com.demo.ciValue.exception.ciValueException;
import com.demo.ciValue.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.demo.ciValue.util.AppConstant.*;


@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void addProduct(List<ProductRequest> request) {

        List<com.demo.ciValue.entity.Product> products=new ArrayList<>();

        for (ProductRequest productRequest : request) {
            Optional<List<com.demo.ciValue.entity.Product>> pDto = productDAO.findByProductId(productRequest.getProductId(),DEFAULT_LIMIT);
            if (pDto.isPresent()&&(!pDto.get().isEmpty()))
                throw new ciValueException("Product Already exists "+pDto.get().get(0).getProductId());
           products.add(com.demo.ciValue.entity.Product.builder().
                   ProductId(productRequest.getProductId())
                   .brand(productRequest.getBrand())
                   .category(productRequest.getCategory()).build()
           );


        }

        productDAO.saveAll(products);

    }


    public Optional<List<com.demo.ciValue.entity.Product>> getProductByCategory(String category, int limit) throws ciValueException {

            Optional<List<com.demo.ciValue.entity.Product>> categoryBasedData= productDAO.findByCategory(category, limit);
            if(categoryBasedData.isPresent()&& (!categoryBasedData.get().isEmpty())){
                return  categoryBasedData;
            }
            else {
                throw new ciValueException(DATA_NOT_FOUND+category+CATEGORY);
            }
        }


    public Optional<List<com.demo.ciValue.entity.Product>> getProductByBrand(String brand, int limit)  {

            Optional<List<com.demo.ciValue.entity.Product>> brandBasedData= productDAO.findByBrand(brand,limit);
            if(brandBasedData.isPresent()&& (!brandBasedData.get().isEmpty())){
                return  brandBasedData;
            }
            else {
                throw new ciValueException(DATA_NOT_FOUND+brand+BRAND);
            }
        }


    public Optional<List<com.demo.ciValue.entity.Product>> getProductByShopperId(String shopperId, int limit) {

        Optional<List<com.demo.ciValue.entity.Product>> shoppperIdBasedData= productDAO.findByShopperId(shopperId,limit);
        if(shoppperIdBasedData.isPresent()&& (!shoppperIdBasedData.get().isEmpty())){
            return  shoppperIdBasedData;
        }
        else {
            throw new ciValueException(DATA_NOT_FOUND+shopperId+SHOPPER_ID);
        }
    }

}
