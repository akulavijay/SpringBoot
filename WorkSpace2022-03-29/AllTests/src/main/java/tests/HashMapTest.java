package tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HashMapTest {
	private final static Predicate<Entry<String, String>> nullEmptyKeysAndValues = Objects::isNull;

	public static void main(String[] args) {
		// filterMap();
		lambda();
	}

	public static void lambda() {
		Map<String, String> valuesToEncrypt = new HashMap<>();
		valuesToEncrypt.put("1", "valueOne");
		valuesToEncrypt.put("2", "valueTwo");
		valuesToEncrypt.put("3", "valueThree");
		valuesToEncrypt.put("4", "");
		valuesToEncrypt.put("5", null);
		valuesToEncrypt.put("", "");
		valuesToEncrypt.put("7", "valueSeven");
		valuesToEncrypt.put(null, "");
		valuesToEncrypt.put("9", "valueEight");
		String k , v;
		for (Map.Entry<String, String> entry : valuesToEncrypt.entrySet()) {
			k = entry.getKey();
			v = entry.getValue();
			if (k == null || k.isEmpty() || v == null || v.isEmpty())
				System.out.println("key or value was null or empty");
			else
				System.out.println(entry);
		}
	}

	public static void filterList() {
		List<String> input = Arrays.asList("A", "B", null, "", "C", "D", null, "", "E", " ");
		List<String> filtered = input.stream().filter(s -> s == null || s.isEmpty()).collect(Collectors.toList());
		System.out.println(filtered);
	}

	public static void filterMap() {
		HashMap<String, String> m = new HashMap<>();
		m.put("Shoes", "200");
		m.put("null", null);
		m.put("Pant", "");
		m.put("", "empty");
		m.put("space", "  ");
		m.put(null, null);
		System.out.println(m);
		Map<String, String> result = m.entrySet().stream().filter(e -> e.getValue() != null && !e.getValue().equals(""))
				.collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		System.out.println(result);
	}

	public static void tests() {
		// create an HashMap
		HashMap<String, String> prices = new HashMap<>();

		// insert entries to the HashMap
		prices.put("Shoes", "200");
		prices.put("Bag", "300");
		prices.put("Pant", "400");
		prices.put("Pant", "");
		prices.put("", "empty");
		prices.put("null", null);
		prices.put(null, null);
		System.out.println("HashMap: " + prices);

		// return set view of all keys
		System.out.println("Keys: " + prices.keySet());
		// System.out.println(prices.keySet().stream().reduce("", (s1, s2) ->
		// String.format("%s, %s", s1, s2)));
		System.out.println(prices.values().removeIf(Objects::isNull));
	}
}
