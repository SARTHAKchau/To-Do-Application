package com.niit.archiveservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Task Already Exists")
public class TaskAlreadyExistsException extends Exception {
}
