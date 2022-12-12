package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <QuestionLink>
 * <QuestionText>
 * Approach:
 * Complexity
 * 1. Time:
 * 2. Space:
 *
 * @author akumar on 11/29/21 IST 9:03 AM
 */

/**
 * [["Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"],["Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"],["Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"],["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]]
 */


public class AccountsMerge {
    public static void main(String[] args) {
//        List<String> list1 = Arrays.asList("Alex", "Alex5@m.co", "Alex4@m.co", "Alex0@m.co");
//        List<String> list2 = Arrays.asList("Ethan", "Ethan3@m.co", "Ethan3@m.co", "Ethan0@m.co");
//        List<String> list3 = Arrays.asList("Kevin", "Kevin4@m.co", "Kevin2@m.co", "Kevin2@m.co");
//        List<String> list4 = Arrays.asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe2@m.co");
//        List<String> list5 = Arrays.asList("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co");
        List<String> list1 = Arrays.asList("David","David0@m.co","David1@m.co");
        List<String> list2 = Arrays.asList("David","David3@m.co","David4@m.co");
        List<String> list3 = Arrays.asList("David","David4@m.co","David5@m.co");
        List<String> list4 = Arrays.asList("David","David2@m.co","David3@m.co");
        List<String> list5 = Arrays.asList("David","David1@m.co","David2@m.co");
        List<List<String>> superList = new ArrayList<>();
        superList.add(list1);
        superList.add(list2);
        superList.add(list3);
        superList.add(list4);
        superList.add(list5);
        List<List<String>> solution = accountsMerge(superList);
        solution.forEach(list -> {
            list.forEach(item -> System.out.print(item + ", "));
            System.out.println();
        });
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> name2Emails = new HashMap<String, Set<String>>();
        Map<String, String> email2Name = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            String key = accounts.get(i).get(0) + "_" + i;
            name2Emails.put(key, new HashSet<>());
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (email2Name.containsKey(email) && !email2Name.get(email).equals(key)) {
                    String keyIndex = email2Name.get(email);
                    for (int k = 1; k < accounts.get(i).size(); k++) {
                        String em = accounts.get(i).get(k);
                        name2Emails.get(keyIndex).add(em);
                        email2Name.put(em, keyIndex);
                    }
                    name2Emails.remove(key);
                    break;
                } else {
                    name2Emails.get(key).add(email);
                    email2Name.put(email, key);
                }
            }
        }

        List<List<String>> list = new ArrayList<>();
        for (String key : name2Emails.keySet()) {
            List<String> subList = new ArrayList<>();
            List<String> emails = new ArrayList<>(name2Emails.get(key));
            Collections.sort(emails);

            int lastIndex = key.lastIndexOf("_");

            subList.add(key.substring(0, lastIndex));
            subList.addAll(emails);
            list.add(subList);
        }
        return list;
    }
}
