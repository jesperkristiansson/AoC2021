package day14;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import helpers.Helpers;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day14/input.txt");
		String polymer = input[0];
		Map<String, String> rules = new HashMap<String, String>();
		for(int i = 2; i < input.length; i++) {
			String[] split = input[i].split(" -> ");
			rules.put(split[0], split[1]);
		}
		Map<String, Long> pairs = new HashMap<String, Long>();
		for(int i = 0; i < polymer.length()-1; i++) {
			String key = String.valueOf(polymer.charAt(i)) + polymer.charAt(i+1);
			putOrAdd(pairs, key, 1L);
		}
		Map<Character, Long> counts = new HashMap<Character, Long>();
		for(char c : polymer.toCharArray()) {
			putOrAdd(counts, c, 1L);
		}
		
		for(int i = 0; i < 40; i++) {
			Map<String, Long> newPairs = new HashMap<String, Long>();
			for(Map.Entry<String, Long> entry : pairs.entrySet()) {
				String key = entry.getKey();
				Long value = entry.getValue();
				if(rules.containsKey(key)) {
					String pair1 = String.valueOf(key.charAt(0)) + rules.get(key);
					String pair2 = String.valueOf(rules.get(key)) + key.charAt(1);
					putOrAdd(newPairs, pair1, value);
					putOrAdd(newPairs, pair2, value);
					putOrAdd(counts, rules.get(key).charAt(0), value);
				}
			}
			pairs = newPairs;
		}
		
		
	/*	
		String ExtendedPolymer = step(polymer, rules, 40);
		Map<Character, Long> counts = new HashMap<Character, Long>();
		for(char c : ExtendedPolymer.toCharArray()) {
			counts.put(c, counts.getOrDefault(c, 0L)+1);
		}	*/
		long max = Long.MIN_VALUE;
		long min = Long.MAX_VALUE;
		for(Map.Entry<Character, Long> entry : counts.entrySet()) {
			if(entry.getValue() > max) {
				 max = entry.getValue();
			}
			if(entry.getValue() < min) {
				min = entry.getValue();
			}
		}
		System.out.println(max-min);
	}
	
	private static <K> void putOrAdd(Map<K, Long> map, K key, Long value) {
		map.put(key, map.getOrDefault(key, 0L)+value);
	}
	
	private static String step(String polymer, Map<String, String> rules, int n) {
		System.out.println(n);
		if(n == 0) {
			return polymer;
		} else {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < polymer.length()-1; i++) {
				sb.append(polymer.charAt(i));
				sb.append(rules.getOrDefault(Character.toString(polymer.charAt(i)) + polymer.charAt(i+1), ""));
			}
			sb.append(polymer.charAt(polymer.length()-1));
			return step(sb.toString(), rules, n-1);
		}
	}
}
