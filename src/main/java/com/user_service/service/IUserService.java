package com.user_service.service;

import com.user_service.entities.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    User registerUser(User user);

}
