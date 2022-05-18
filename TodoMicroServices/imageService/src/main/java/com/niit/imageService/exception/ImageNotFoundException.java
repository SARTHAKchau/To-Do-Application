package com.niit.imageService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND , reason = "Image with specified id is not found")
public class ImageNotFoundException extends Throwable {
}
