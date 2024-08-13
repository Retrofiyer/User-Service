package com.user_service.message;

import com.user_service.entities.User;
import com.user_service.entities.UserMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public UserMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(User user) {
        UserMessage userMessage = new UserMessage();
        userMessage.setId(user.getId());
        userMessage.setFirstName(user.getFirstName());
        userMessage.setLastName(user.getLastName());
        userMessage.setEmail(user.getEmail());
        userMessage.setPassword(user.getPassword());
        userMessage.setEnabled(user.isEnabled());
        userMessage.setToken(user.getToken());
        rabbitTemplate.convertAndSend("token.queue", userMessage);
    }

    public void updateUserWithToken(UserMessage userMessage) {
        rabbitTemplate.convertAndSend("user.update.queue", userMessage);
    }
}
