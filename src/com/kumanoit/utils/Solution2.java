package com.kumanoit.utils;

import org.apache.logging.log4j.Logger;

public class Solution2 {

    public static void main(String[] args) {

    }

    private static void test(String number) {
        try {
            Long.parseLong(number);
        } catch ( NumberFormatException e) {
        }
    }
}
