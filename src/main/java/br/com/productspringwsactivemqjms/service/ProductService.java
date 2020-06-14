package br.com.productspringwsactivemqjms.service;

import br.com.productspringwsactivemqjms.dto.ProductDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<Void> addInQueue(ProductDto product);

    void addIndDatabase(ProductDto product);
}
