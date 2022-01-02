package day06;

import helpers.Helpers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] nums = Helpers.fileAsStrings("src/day06/input.txt")[0].split(",");
		HashMap<LanternFish, Long> fishes = new HashMap<>();
		for (String num : nums) {
			LanternFish tempFish = new LanternFish(Integer.parseInt(num));
			fishes.put(tempFish, fishes.getOrDefault(tempFish, (long) 0) + 1);
		}
		for (int i = 0; i < 256; i++) {
			HashMap<LanternFish, Long> newMap = new HashMap<LanternFish, Long>();
			for (Map.Entry<LanternFish, Long> entry : fishes.entrySet()) {
				if (entry.getKey().step()) {
					LanternFish newFish = new LanternFish(8);
					if (newMap.containsKey(newFish)) {
						newMap.put(newFish, newMap.get(newFish) + entry.getValue());
					} else {
						newMap.put(newFish, entry.getValue());
					}
				}
			}
			addFrom(fishes, newMap);
			fishes = updateMap(fishes);
		}
		long amount = 0;
		for (Map.Entry<LanternFish, Long> f : fishes.entrySet()) {
			amount += f.getValue();
		}
		System.out.println(amount);
	}

	private static void addFrom(Map<LanternFish, Long> org, Map<LanternFish, Long> temp) {
		for (Map.Entry<LanternFish, Long> entry : temp.entrySet()) {
			org.put(entry.getKey(), org.getOrDefault(entry.getKey(), (long) 0) + entry.getValue());
		}
	}
	
	private static HashMap<LanternFish, Long> updateMap(Map<LanternFish, Long> map) {
		HashMap<LanternFish, Long> newMap = new HashMap<LanternFish, Long>();
		addFrom(newMap, map);
		return newMap;
	}
}
