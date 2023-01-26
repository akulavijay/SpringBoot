package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StreamTest {
	public static void main(String args[]) {
		try {
			Map<String, String> data = new HashMap<String, String>();
			data.put(null, null);
			data.put("K1", "V1");
			data.put("", "V1");
			/*
			 * data.entrySet().stream() .filter(Objects::nonNull) .filter(entity ->
			 * entity.getKey() != null) .filter(entity -> entity.getValue() != null)
			 * .filter(entity -> !entity.getValue().isEmpty()) .forEach( entry -> {
			 * System.out.println(entry); });
			 */
			data.entrySet().stream().filter(Objects::nonNull).forEach(entry -> {
				System.out.println(entry);
			});
		} catch (Exception e) {
			System.out.println("Error in StreamTest");
		}
	}
}
