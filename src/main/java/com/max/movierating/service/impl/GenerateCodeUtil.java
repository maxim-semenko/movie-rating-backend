package com.max.movierating.service.impl;

public class GenerateCodeUtil {

    public static Integer generateCode() {
        return (int) (Math.random() * 8999 + 1000);
    }

}
