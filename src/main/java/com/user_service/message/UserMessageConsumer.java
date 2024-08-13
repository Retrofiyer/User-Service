package com.user_service.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.user_service.entities.UserMessage;
import com.user_service.repository.UserRepository;
import com.user_service.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMessageConsumer {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RabbitListener(queues = "user.update.queue")
    public void consumeMessage(UserMessage userMessage) {
        try {
            if (userMessage.getId() == null || userMessage.getId() <= 0) {
                System.out.println("Error: User ID is null or empty");
                return;
            }

            System.out.println("Message received in user-service: " + userMessage);

            Optional<User> optionalUser = userRepository.findById(userMessage.getId());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (userMessage.getToken() != null) {
                    user.setToken(userMessage.getToken());
                }
                if (userMessage.getFirstName() != null) {
                    user.setFirstName(userMessage.getFirstName());
                }
                if (userMessage.getLastName() != null) {
                    user.setLastName(userMessage.getLastName());
                }
                if (userMessage.getEmail() != null) {
                    user.setEmail(userMessage.getEmail());
                }

                if (userMessage.isEnabled()) {
                    user.setEnabled(true);
                }

                userRepository.save(user);

                userMessage.setFirstName(user.getFirstName());
                userMessage.setLastName(user.getLastName());
                userMessage.setEmail(user.getEmail());
                userMessage.setPassword(user.getPassword());
                userMessage.setEnabled(user.isEnabled());

                rabbitTemplate.convertAndSend("email.queue", userMessage);
                rabbitTemplate.convertAndSend("auth.queue", userMessage);
                rabbitTemplate.convertAndSend("auth.response.queue", userMessage);

            } else {
                System.out.println("User not found for ID: " + userMessage.getId());
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while processing the message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "auth.queue")
    public void consumeAuthMessage(UserMessage userMessage) {
        System.out.println("Authentication request received: " + userMessage);

        Optional<User> optionalUser = userRepository.findByEmail(userMessage.getEmail());
        boolean isValidUser = false;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            isValidUser = passwordEncoder.matches(userMessage.getPassword(), user.getPassword());
        }

        userMessage.setEnabled(isValidUser);
        rabbitTemplate.convertAndSend("auth.response.queue", userMessage);
    }

    @RabbitListener(queues = "auth.verification.queue")
    public void consumeVerificationRequest(UserMessage userMessage) {
        System.out.println("Authentication verification received: " + userMessage);
        Optional<User> optionalUser = userRepository.findByEmail(userMessage.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserMessage responseMessage = new UserMessage();
            responseMessage.setId(userMessage.getId());
            responseMessage.setEmail(user.getEmail());
            responseMessage.setPassword(user.getPassword());
            responseMessage.setEnabled(user.isEnabled());

            rabbitTemplate.convertAndSend("auth.response.queue", responseMessage);
        }
    }
}
