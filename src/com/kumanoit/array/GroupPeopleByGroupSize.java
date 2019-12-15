package com.kumanoit.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/
 * 
 * @author kumanoit 15-Dec-2019 6:30:43 PM
 * 
 *         Algorithm: Keep a hashmap with key as group size and value as list of
 *         ids Output should contain list of people in each group. So, size of
 *         each group will be picked up from key of hashmap and people ids can
 *         be picked up from value of hashmap. There might be cases where the
 *         list of values in map is more than key. In that case create another
 *         list of people ids.
 */
public class GroupPeopleByGroupSize {

	public static void main(String[] args) {
		test(new int[] { 1, 1, 1, 1, 1 });
	}

	private static void test(int[] groupSizes) {
		System.out.print("[");
		groupThePeople(groupSizes).forEach(group -> {
			System.out.print("[");
			group.forEach(person -> {
				System.out.print(person + ",");
			});
			System.out.print("]");
		});
		System.out.print("]");
	}

	public static List<List<Integer>> groupThePeople(int[] groupSizes) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < groupSizes.length; i++) {
			if (!map.keySet().contains(groupSizes[i])) {
				map.put(groupSizes[i], new ArrayList<Integer>());
			}
			map.get(groupSizes[i]).add(i);
		}
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		for (int key : map.keySet()) {
			List<Integer> ids = map.get(key);
			while (ids.size() >= key) {
				List<Integer> sol = new ArrayList<Integer>();
				for (int i = 0; i < key; i++) {
					sol.add(ids.remove(0));
				}
				solution.add(sol);
			}
		}
		return solution;
	}
}
