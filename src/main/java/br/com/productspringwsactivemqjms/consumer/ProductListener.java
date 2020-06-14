package br.com.productspringwsactivemqjms.consumer;

import br.com.productspringwsactivemqjms.dto.ProductDto;
import br.com.productspringwsactivemqjms.service.ProductService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ProductListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    public ProductListener(ProductService productService) {
        this.productService = productService;
    }

    @JmsListener(destination = "queue.product", containerFactory = "jmsListenerContainerFactory")
    public void addInDatabase(final String jsonProduct) {
        Gson gson = new Gson();
        logger.info(">>>>>>>>> RECEIVED THE PRODUCT: {}", jsonProduct);
        productService.addIndDatabase(gson.fromJson(jsonProduct, ProductDto.class));
        logger.info(">>>>>>>>> PRODUCT SUCCESSFULLY ADDED TO DATABASE.");

    }

}
