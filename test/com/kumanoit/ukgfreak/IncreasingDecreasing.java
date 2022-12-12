package com.kumanoit.ukgfreak;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 1/31/21 IST 3:15 PM
 */
public class IncreasingDecreasing {

    public static void main(String[] args) {
        System.out.println(findPossibleSmallestNumberMatchingPattern(null));
        System.out.println(findPossibleSmallestNumberMatchingPattern(""));
        System.out.println(findPossibleSmallestNumberMatchingPattern("NNNNNNNNN"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("NNNNNNNN"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("MMMMMMMMM"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("MMMMMMMM"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("M"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("N"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("NMN"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("MNN"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("NMM"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("MNM"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("MNN"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("NNN"));
        System.out.println(findPossibleSmallestNumberMatchingPattern("MMM"));
    }

    private static int findPossibleSmallestNumberMatchingPattern(String pattern) {
        if (pattern == null || pattern.isEmpty() || pattern.length() > 8) {
            return -1;
        }

        char[] solution = new char[pattern.length() + 1];
        int counter = 1;
        for (int end = 0; end <= pattern.length(); end++) {
            if (end < pattern.length() && pattern.charAt(end) != 'M' && pattern.charAt(end) != 'N') {
                return -1;
            }
            if (end == pattern.length() || pattern.charAt(end) == 'N') {
                for (int pre = end - 1; pre >= -1; pre--) {
                    solution[pre + 1] = (char) ((int) '0' + counter++);
                    if (pre >= 0 && pattern.charAt(pre) == 'N') {
                        break;
                    }
                }
            }
        }
        return Integer.parseInt(new String(solution));
    }
}
