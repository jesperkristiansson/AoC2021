package day12;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import helpers.Helpers;

public class Pathing {
	public static void main(String[] args) throws IOException {
		String[] input = Helpers.fileAsStrings("src/day12/input.txt");
		Map<String, List<String>> connections = new HashMap<String, List<String>>();
		for (String s : input) {
			String c1 = s.split("-")[0];
			String c2 = s.split("-")[1];
			connections.putIfAbsent(c1, new ArrayList<String>());
			connections.putIfAbsent(c2, new ArrayList<String>());
			if (!c2.equals("start")) {
				connections.get(c1).add(c2);
			}
			if (!c1.equals("start")) {
				connections.get(c2).add(c1);
			}
		}
		int paths = traverse(connections, "start", new HashSet<String>(), new HashSet<String>());
		System.out.println(paths);
	}

	private static int traverse(Map<String, List<String>> connections, String node, Set<String> visited, Set<String> visited2) {
		if (node.equals("end")) {
			return 1;
		} else {
			Set<String> vis = new HashSet<String>(visited);
			Set<String> vis2 = new HashSet<String>(visited2);
			if (isLowerCase(node)) {
				if(vis.contains(node)) {
					vis2.add(node);
				}
				vis.add(node);
			}
			int ret = 0;
			for (String next : connections.get(node)) {
				if (!vis.contains(next) || vis2.isEmpty()) {
					ret += traverse(connections, next, vis, vis2);
				}
			}
			return ret;
		}
	}

	private static boolean isLowerCase(String s) {
		return s.equals(s.toLowerCase());
	}
}
