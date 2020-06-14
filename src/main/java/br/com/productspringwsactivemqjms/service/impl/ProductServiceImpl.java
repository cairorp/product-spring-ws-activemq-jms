package br.com.productspringwsactivemqjms.service.impl;

import br.com.productspringwsactivemqjms.client.ProductClient;
import br.com.productspringwsactivemqjms.dto.ProductDto;
import br.com.productspringwsactivemqjms.exception.ProductException;
import br.com.productspringwsactivemqjms.exception.QueueException;
import br.com.productspringwsactivemqjms.repository.ProductRepository;
import br.com.productspringwsactivemqjms.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductClient productClient;
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductClient productClient, ProductRepository productRepository) {
        this.productClient = productClient;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> addInQueue(ProductDto product) {
        try {
            productClient.add(product);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception ex){
            throw new QueueException(">>>>>> AN ERROR OCCURRED WHEN TRYING TO ADD THE PRODUCT TO THE QUEUE.");
        }
    }

    @Override
    public void addIndDatabase(ProductDto product) {
        try{
            productRepository.save(product.toEntity());
        }catch (Exception ex){
            throw new ProductException(">>>>>>> AN ERROR OCCURRED WHEN ATTEMPTING TO SAVE THE PRODUCT IN THE DATABASE: "
                    + product.toString());
        }
    }
}
