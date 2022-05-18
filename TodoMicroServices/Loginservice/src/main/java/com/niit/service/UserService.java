package com.niit.service;

import com.niit.domain.User;
import com.niit.exception.UserAlreadyExistsException;
import com.niit.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    public User findByUsernameAndPassword(String userName,String password) throws UserNotFoundException;
    List<User> getAllUser();
    User getUser(String username);
}
