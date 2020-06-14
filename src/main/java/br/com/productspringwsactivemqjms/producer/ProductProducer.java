package br.com.productspringwsactivemqjms.producer;

import br.com.productspringwsactivemqjms.dto.ProductDto;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Queue;

@Component
public class ProductProducer {

    private final JmsTemplate jmsTemplate;
    private final Queue queue;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ProductProducer(JmsTemplate jmsTemplate, Queue queue) {
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
    }

    public void add(ProductDto productDto) throws JMSException {
        logger.info(">>>>>>>>> SENDING THE PRODUCT [{}] TO QUEUE [{}]", productDto, queue.getQueueName());
        Gson gson = new Gson();
        jmsTemplate.convertAndSend(queue, gson.toJson(productDto));
        logger.info(">>>>>>>>> SUCCESSFULLY SHIPPED PRODUCT.");
    }
}