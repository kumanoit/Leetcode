package com.kumanoit.strings;

import com.kumanoit.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 8/10/20 IST 12:59 AM
 */
public class ExpressionAddOperators {

    public static void main(String[] args) {
        test("123", 6);
    }

    private static void test(String num, int target) {
        ListUtils.printList(addOperators(num, target));
    }
    private static List<String> addOperators(String num, int target) {
        List<String> solution = new ArrayList<>();
        count(num, target, 0, solution, "");
        return solution;
    }

    private static void count(String num, int target, int index, List<String> solution, String output) {
        if (index == num.length()) {
            if (compute(output) == target) {
                solution.add(output);
            }
            return;
        }

        if (output.length() == 0) {
            count(num, target, index + 1, solution, "" + num.charAt(index));
        } else {
            count(num, target, index + 1, solution, output + "+" + num.charAt(index));
            count(num, target, index + 1, solution, output + "-" + num.charAt(index));
            count(num, target, index + 1, solution, output + "*" + num.charAt(index));
        }
    }

    private static int compute(String input) {
        Stack<Integer> operands = new Stack<Integer>();
        Stack<Character> operators = new Stack<Character>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isNumber(ch)) {
                operands.push(ch - '0');
            } else {
                if (ch == '*' || ch == '/') {
                    operators.push(ch);
                } else {
                    while (!operators.isEmpty() && (operators.peek() == '*' || operators.peek() == '/')) {
                        int operand1 = operands.pop();
                        int operand2 = operands.pop();
                        char operator = operators.pop();
                        int result = operator == '*' ? operand1 * operand2 : operand2 / operand1;
                        operands.push(result);
                    }
                    operators.push(ch);
                }
            }
        }
        while (!operators.isEmpty()) {
            int operand1 = operands.pop();
            int operand2 = operands.pop();
            int operator = operators.pop();
            int result = operator == '*' ? operand1 * operand2 :
                    operator == '+' ? operand1 + operand2 :
                            operand2 - operand1;
            operands.push(result);
        }
        return operands.pop();
    }

    private static boolean isNumber(char ch) {
        return (ch >= '0' && ch <= '9');
    }
}
