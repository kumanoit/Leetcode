package com.kumanoit.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/subdomain-visit-count/
 * 
 * @author kumanoit
 * 24-Dec-2019 9:49:23 PM
 */
public class SubdomainVisitCount {

	public static void main(String[] args) {
		test(new String[] {"1 google.com", "2 photos.google.com", "3 mail.google.com"});
		test(new String[] {"1 ..."});
		test(new String[] {"1 abc", "2 def"});
	}

	private static void test(String[] domains) {
		subdomainVisits(domains).forEach(item -> {
			System.out.println(item);
		});
	}

	public static List<String> subdomainVisits(String[] cpdomains) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < cpdomains.length; i++) {
			String[] parts = cpdomains[i].split("\\s+");
			Integer count = Integer.parseInt(parts[0]);
			String value = parts[1];
			// System.out.println(parts[0] + " >> " + parts[1]);
			while (true) {
				if (!map.keySet().contains(value)) {
					map.put(value, 0);
				}
				map.put(value, map.get(value) + count);
				int indexOfDot = value.indexOf('.');
				if (indexOfDot == -1) {
					break;
				}
				value = value.substring(indexOfDot + 1);
				// System.out.println(indexOfDot + ">>" + value);
			}
		}
		List<String> solution = new ArrayList<String>();
		map.forEach((value, count) -> {
			solution.add(count + " " + value);
		});
		return solution;
	}
}
