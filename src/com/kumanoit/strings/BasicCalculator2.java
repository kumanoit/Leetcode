package com.kumanoit.strings;

import java.util.Stack;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/10/20 IST 1:49 AM
 */
public class BasicCalculator2 {

    public static void main(String[] args) {
        System.out.println(calculate("1-1+1"));
        System.out.println(calculate("1-1-1"));
    }
    public static int calculate(String s) {
        if (s.trim().length() == 0) {
            return 0;
        }
        Stack<Integer> operands = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();
        s = s.trim();
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (isOperator(ch)) {
                int num = Integer.parseInt(s.substring(start, i).trim());
                operands.push(num);
                start = i + 1;
                while (!operators.isEmpty() && ((operators.peek() == '*' || operators.peek() == '/') ||
                        (operators.peek() == '+' && ch == '-') || (operators.peek() == '-' && ch == '+') ||
                        (operators.peek() == '-' && ch == '-') || (operators.peek() == '+' && ch == '+'))) {
                    int operand1 = operands.pop();
                    int operand2 = operands.pop();
                    char operator = operators.pop();
                    int result = 0;
                    if (operator == '*') {
                        result = operand2 * operand1;
                    } else if (operator == '/') {
                        result = operand2 / operand1;
                    } else if (operator == '+') {
                        result = operand2 + operand1;
                    } else {
                        result = operand2 - operand1;
                    }
                    operands.push(result);
                }
                operators.push(ch);
            }
        }
        operands.push(Integer.parseInt(s.substring(start).trim()));

        while(!operators.isEmpty()) {
            int operand1 = operands.pop();
            int operand2 = operands.pop();
            char operator = operators.pop();
            int result = 0;
            if (operator == '*') {
                result = operand2 * operand1;
            } else if (operator == '/') {
                result = operand2 / operand1;
            } else if (operator == '+') {
                result = operand2 + operand1;
            } else {
                result = operand2 - operand1;
            }
            operands.push(result);
        }

        return operands.pop();
    }


    private static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*'|| ch == '/';
    }
}
