package com.max.movierating.service.impl;

import java.security.SecureRandom;

public class GenerateCodeUtil {

    public static Integer generateCode() {
        SecureRandom random = new SecureRandom();
        int max = 9999;
        int min = 1000;

        return random.nextInt(max - min + 1) + min;
    }

}
