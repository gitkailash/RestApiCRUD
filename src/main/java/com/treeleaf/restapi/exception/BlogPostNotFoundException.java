package com.treeleaf.restapi.exception;

public class BlogPostNotFoundException extends RuntimeException{
    public BlogPostNotFoundException(String msg){
        super(msg);
    }
}
