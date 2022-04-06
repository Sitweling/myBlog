package com.sinn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/6
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundBlogException extends RuntimeException {
    public NotFoundBlogException() {
    }

    public NotFoundBlogException(String message) {
        super(message);
    }

    public NotFoundBlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
