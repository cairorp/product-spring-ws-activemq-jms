package br.com.productspringwsactivemqjms.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@SuppressWarnings("ALL")
@EnableJms
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
    @SuppressWarnings("jmsListenerContainerFactory")
    public JmsListenerContainerFactory jmsListenerContainerFactory(DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, activeMQConnectionFactory());
        factory.setPubSubDomain(true);

        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
    }

    @Bean
    public JmsTemplate jmsTemplateTopic() {
        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory());
        jmsTemplate.setPubSubDomain(true);

        return jmsTemplate;
    }

    private ActiveMQConnectionFactory activeMQConnectionFactory() {
        return user.isEmpty() ?
                new ActiveMQConnectionFactory(brokerUrl) :
                new ActiveMQConnectionFactory(user, password, brokerUrl);
    }

}
