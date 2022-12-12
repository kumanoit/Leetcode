package com.ukgfreak;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 3/4/22 IST 6:08 PM
 */
public class SampleTest {
    public static void main(String[] args) {
        List<List<String>> employees = Arrays.asList(Arrays.asList("Sacin", "Taryun"), Arrays.asList("Jack", "Michael"),
                Arrays.asList("Sam","Gopal","Ankit"), Arrays.asList("Anil"));
        List<String> result = employees.stream().flatMap(name -> name.stream()).filter(s -> s.startsWith("A")).collect(Collectors.toList());
        result.forEach(System.out::print);
    }

}
