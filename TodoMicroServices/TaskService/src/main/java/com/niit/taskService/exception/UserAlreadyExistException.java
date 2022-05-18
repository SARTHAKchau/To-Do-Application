package com.niit.taskService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User already Exist")
public class UserAlreadyExistException extends Exception{
}
