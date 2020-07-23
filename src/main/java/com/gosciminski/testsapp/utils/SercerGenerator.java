package com.gosciminski.testsapp.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class SercerGenerator {
    
    public String generateSecret(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
     
        String generatedString = random.ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        return generatedString;
    }
}