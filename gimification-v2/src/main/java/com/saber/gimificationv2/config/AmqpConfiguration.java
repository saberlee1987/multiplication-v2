package com.saber.gimificationv2.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AmqpConfiguration implements RabbitListenerConfigurer {

    @Value(value = "${spring.rabbitmq.exchange.attempts}")
    private String exchangeAttempts;
    @Value(value = "${spring.rabbitmq.queue.gamification}")
    private String gamificationQueue;
    @Value(value = "${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Bean
    public TopicExchange topicExchange(){
        return ExchangeBuilder.topicExchange(exchangeAttempts)
                .durable(true).build();
    }
    @Bean
    public Queue gamificationQueue(){
        return QueueBuilder.durable(gamificationQueue)
                .build();
    }

    @Bean
    public Binding correctAttemptBinding(Queue gamificationQueue,TopicExchange attemptExchange){
        return BindingBuilder.bind(gamificationQueue)
                .to(attemptExchange)
                .with(routingKey);
    }
    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES)
        );
        return messageConverter;
    }
    @Bean
    public MessageHandlerMethodFactory methodFactory(){
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();

        factory.setMessageConverter(messageConverter());

        return factory;
    }

//    @Bean
//    public RabbitListenerConfigurer rabbitListenerConfigurer(MessageHandlerMethodFactory factory){
//        return (c) -> c.setMessageHandlerMethodFactory(factory);
//    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(methodFactory());
    }
}