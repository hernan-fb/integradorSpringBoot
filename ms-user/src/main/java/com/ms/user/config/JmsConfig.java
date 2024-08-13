package com.ms.user.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfig {
    //configuraciones del active mq
    @Bean
    @Primary //Indica que es una conexion principal
    public ActiveMQConnectionFactory connectionFactory() { //se conecta
        ActiveMQConnectionFactory connection = new ActiveMQConnectionFactory();
        connection.setBrokerURL("tcp://host.docker.internal:61616");
        connection.setUserName("admin");
        connection.setPassword("admin");

        return connection;
    }

    public JmsTemplate jmsTemplate() { //produce el msj
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory() {
        // lanza y escucha
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }
}
