package com.visenze.productcat.android;


public class ProductCatException extends RuntimeException {

    public ProductCatException(String message) {
        super(message);
    }

    public ProductCatException(String message, Throwable e) {
        super(message, e);
    }

}
