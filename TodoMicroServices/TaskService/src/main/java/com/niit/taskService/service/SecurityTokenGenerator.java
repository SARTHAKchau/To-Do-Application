package com.niit.taskService.service;

import com.niit.taskService.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}
