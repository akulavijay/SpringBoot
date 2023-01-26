package tests;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TreeMapTest extends TreeMap {
	private final Predicate<Entry<String, String>> nonEmptyKeysAndValues = entry -> entry != null
			&& entry.getKey() != null && !entry.getKey().isEmpty() && entry.getValue() != null
			&& !entry.getValue().isEmpty();

	public static void main(String[] args) {
		TreeMapTest test = new TreeMapTest();
		TreeMap<String, String> tm = new TreeMap<>();
		tm.put("1", "One");
		tm.put("2", "Two");
		tm.put("", "");
		tm.put("null", null);
		tm.put("6", "Six");
		tm.put("space", " ");
		tm.put("8", "Eight");
		System.out.println(test.removeNulls(tm));
	}

	
	public <K, V> Map<String,String> removeNulls(Map<String,String> original) {
		  return original.entrySet().stream().filter(nonEmptyKeysAndValues)
		                 .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		}
}
