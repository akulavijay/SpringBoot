package encryption;

import java.util.HashMap;
import java.util.Map;

public class Tests {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put(null, "null");
		for (String key : map.keySet())
			System.out.println(key);
	}
}
