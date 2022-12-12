package com.kumanoit.strings;

import java.util.Arrays;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 11/7/21 IST 11:01 AM
 */
public class MultiplyNumbers {
    public static void main(String[] args) {
//        System.out.println(multiply("2", "3"));
        System.out.println(multiply("123", "456"));
    }

    public static String multiply(String num1, String num2) {
        char[] input1 = num1.toCharArray();
        char[] input2 = num2.toCharArray();
        char[] product = new char[num1.length() + num2.length()];
        Arrays.fill(product, '0');
        if (input1.length > input2.length) {
            char[] temp = input1;
            input1 = input2;
            input2 = temp;
        }

        int lastIndex = product.length;
        for (int i = input1.length - 1; i >= 0; i--) {
            multiply(input2, --lastIndex, input1[i] - '0', product);
        }

        String output = null;
        for (int i = 0; i < product.length; i++) {
            if (product[i] != '0') {
                return new String(Arrays.copyOfRange(product, i, product.length));
            }
        }
        return output;

    }

    private static void multiply(char[] input, int lastIndex, int digit, char[] product) {
        int carry = 0;
        int sum = 0;
        int i = 0;

        for (i = input.length - 1; i >= 0; i--) {
            sum = (input[i] - '0') * digit + carry + (product[lastIndex] - '0');
            product[lastIndex] = (char) (sum % 10 + '0');
            lastIndex--;
            carry = sum / 10;
        }

        if (carry > 0) {
            product[lastIndex] = (char) (carry + '0');
        }
    }
}
