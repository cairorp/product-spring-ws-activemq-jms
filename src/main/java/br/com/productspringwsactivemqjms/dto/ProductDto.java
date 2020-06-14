package br.com.productspringwsactivemqjms.dto;

import br.com.productspringwsactivemqjms.model.Product;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class ProductDto implements Serializable {
    private String name;
    private BigDecimal price;

    public Product toEntity() {
        return new Product(this.name,
                           this.price);
    }
}
