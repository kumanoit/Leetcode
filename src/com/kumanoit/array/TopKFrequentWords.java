package com.kumanoit.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.com/problems/top-k-frequent-words/solution/
 * 
 * @author kumanoit 18-Dec-2019 9:37:14 PM
 * 
 *         Approach: Keep a count of number of times a word appear. Later save
 *         all numbers in a heap which compares on the basis of count. Pop
 *         element from heap and keep it in a list as long as the count remains
 *         same and keep it in a list. Keep on saving element from the previous
 *         list in final list, such that total elements in final list shouldn't
 *         exceed k.
 */
public class TopKFrequentWords {

	public static void main(String[] args) {
		test(new String[] { "i", "love", "leetcode", "i", "love", "coding", "leetcode", "a", "a", "a", "a" }, 3);
		test(new String[] { "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is" }, 4);
	}

	private static void test(String[] words, int k) {
		System.out.println(topKFrequent(words, k));
		System.out.println(givenSolution(words, k));
	}

	public static List<String> topKFrequent(String[] words, int k) {
		List<String> solution = new ArrayList<String>();
		if (k == 0) {
			return solution;
		}
		Map<String, WordCount> map = new HashMap<>();
		Queue<WordCount> heap = new PriorityQueue<WordCount>();
		for (int i = 0; i < words.length; i++) {
			if (!map.keySet().contains(words[i])) {
				WordCount wordCount = new WordCount(words[i]);
				map.put(words[i], wordCount);
			} else {
				map.get(words[i]).count++;
			}
		}
		for (String key : map.keySet()) {
			heap.add(map.get(key));
		}
		int i = 0;
		while (i < k) {
			WordCount wordCount = heap.remove();
			List<String> localSolution = new ArrayList<String>();
			localSolution.add(wordCount.word);
			while (!heap.isEmpty() && wordCount.count == heap.peek().count) {
				localSolution.add(heap.remove().word);
			}
			Collections.sort(localSolution);
			while (i < k && !localSolution.isEmpty()) {
				solution.add(localSolution.remove(0));
				i++;
			}
		}
		return solution;
	}

	/**
	 * There are few good things to note here. Map initialization the gerOrdefault
	 * use and lambda to sort
	 * 
	 * @param words
	 * @param k
	 * @return
	 */
	public static List<String> givenSolution(final String[] words, int k) {
		Map<String, Integer> wordCountMap = new HashMap<>();
		for (String word : words) {
			wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
		}
		List<String> candidates = new ArrayList<String>(wordCountMap.keySet());
		Collections.sort(candidates, 
				(word1, word2) -> 
					wordCountMap.get(word1) == wordCountMap.get(word2) ? 
							word1.compareTo(word2) : // sort by name alphabetically
							wordCountMap.get(word2) - wordCountMap.get(word1) // reverse sort by count
				);
		return candidates.subList(0, k);
	}
}

class WordCount implements Comparable<WordCount> {
	String word;
	int count;

	WordCount(String word) {
		this.word = word;
		this.count = 1;
	}

	@Override
	public int compareTo(WordCount wordCount) {
		if (wordCount.count == this.count) {
			return wordCount.word.compareTo(this.word);
		}
		return wordCount.count - this.count;
	}
}