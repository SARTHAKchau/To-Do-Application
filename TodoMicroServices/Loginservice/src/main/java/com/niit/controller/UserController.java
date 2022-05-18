package com.niit.controller;

import com.niit.domain.User;
import com.niit.exception.UserAlreadyExistsException;
import com.niit.exception.UserNotFoundException;
import com.niit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    private ResponseEntity responseEntity;

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user) throws UserAlreadyExistsException {

        return responseEntity = new ResponseEntity(userService.saveUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public Object loginUser(@RequestBody User user) throws UserNotFoundException {

        Object obj=null;
        try{
            obj = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        }
        catch (UserNotFoundException e) {
            obj=null;
        }
        return obj;
    }

    @GetMapping("users")
    public ResponseEntity getAllUsers(HttpServletRequest request){
        List<User> list =  userService.getAllUser();
        responseEntity = new ResponseEntity(list,HttpStatus.OK);
        return responseEntity;

    }
    @GetMapping("/users/{username}")
    public  ResponseEntity getUser(@PathVariable("username") String username)
    {
        User user=userService.getUser(username);
        responseEntity=new ResponseEntity<>(user,HttpStatus.OK);
        return responseEntity;
    }
}
