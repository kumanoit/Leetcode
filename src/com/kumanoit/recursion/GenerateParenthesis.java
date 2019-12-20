package com.kumanoit.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/submissions/
 * 
 * Algorithm: Create parenthesis in recursive manner.
 * 
 * @author kumanoit 20-Dec-2019 10:22:29 PM
 */
public class GenerateParenthesis {

	public static void main(String[] args) {
		test(0);
		test(1);
		test(2);
		test(3);
	}

	private static void test(int n) {
		List<String> solution = generateParenthesis(n);
		if (solution != null && !solution.isEmpty()) {
			solution.forEach(item -> System.out.println(item));
		}
		System.out.println();
	}

	private static List<String> generateParenthesis(int n) {
		if (n == 0) {
			return null;
		}
		List<String> solution = new ArrayList<String>();
		generate(n, 0, "", solution);
		return solution;
	}

	private static void generate(int open, int closed, String string, List<String> solutionSet) {
		if (open == 0 && closed == 0) {
			solutionSet.add(string);
			return;
		}
		if (open > 0) {
			generate(open - 1, closed + 1, string + "(", solutionSet);
		}
		if (closed > 0) {
			generate(open, closed - 1, string + ")", solutionSet);
		}
	}
}
