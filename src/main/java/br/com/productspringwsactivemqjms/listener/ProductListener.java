package br.com.productspringwsactivemqjms.listener;

import br.com.productspringwsactivemqjms.dto.ProductDto;
import br.com.productspringwsactivemqjms.service.ProductService;
import br.com.productspringwsactivemqjms.utils.Constants;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

import static br.com.productspringwsactivemqjms.utils.Constants.*;

@Component
public class ProductListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    private CountDownLatch latch = new CountDownLatch(1);

    public ProductListener(ProductService productService) {
        this.productService = productService;
    }

    @JmsListener(destination = PRODUCT_QUEUE)
    public void addInDatabase(String productJson) {
        logger.info(">>>>>>>>> RECEIVED THE PRODUCT: {}", productJson);
        Gson gson = new Gson();
        productService.addIndDatabase(gson.fromJson(productJson, ProductDto.class));
        logger.info(">>>>>>>>> PRODUCT SUCCESSFULLY ADDED TO DATABASE.");
        latch.countDown();
    }
}
