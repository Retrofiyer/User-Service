package com.user_service.config;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue emailQueue() {
        return new Queue("email.queue");
    }

    @Bean
    public Queue tokenQueue() {
        return new Queue("token.queue");
    }

    @Bean
    public Queue authQueue() {
        return new Queue("auth.queue");
    }

    @Bean
    public Queue authResponseQueue() {
        return new Queue("auth.response.queue");
    }

    @Bean
    public Queue userUpdateQueue() {
        return new Queue("user.update.queue");
    }

    @Bean
    public Queue authVerificationQueue() {
        return new Queue("auth.verification.queue");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        rabbitTemplate.setMessageConverter(jsonMessageConverter());

        return rabbitTemplate;
    }
}
