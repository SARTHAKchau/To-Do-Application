package com.niit.service;

import com.niit.domain.User;
import com.niit.exception.UserAlreadyExistsException;
import com.niit.exception.UserNotFoundException;
import com.niit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
//        if(userRepository.findById(user.getUsername()).isPresent())
//        {
//            throw new UserAlreadyExistsException();
//        }
        return userRepository.save(user);
    }

    @Override
    public User findByUsernameAndPassword(String userName, String password) throws UserNotFoundException {
        User user=userRepository.findByUsernameAndPassword(userName,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        System.out.println("user is"+user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) {
        User user=userRepository.findByUsername(username);
        System.out.println("user is +"+user);
        return user;
    }


}
