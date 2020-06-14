package br.com.productspringwsactivemqjms.client;

import br.com.productspringwsactivemqjms.dto.ProductDto;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static br.com.productspringwsactivemqjms.utils.Constants.PRODUCT_QUEUE;

@Component
public class ProductClient {

    private JmsTemplate jmsTemplate;

    public ProductClient(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void add(ProductDto productDto) {
        jmsTemplate.convertAndSend(PRODUCT_QUEUE, productDto.toEntity());
    }
}
