package br.com.productspringwsactivemqjms.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
public class JmsConfiguration {

    private final String brokerUrl;
    private final String user;
    private final String password;

    public JmsConfiguration(@Value("${spring.activemq.broker-url}") String brokerUrl,
                            @Value("${spring.activemq.user}") String user,
                            @Value("${spring.activemq.password}") String password) {
        this.brokerUrl = brokerUrl;
        this.user = user;
        this.password = password;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
    }

    @Bean
    public Queue productQueue() {
        return new ActiveMQQueue("queue.product");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        return user.isEmpty() ?
                new ActiveMQConnectionFactory(brokerUrl) :
                new ActiveMQConnectionFactory(user, password, brokerUrl);
    }

}
