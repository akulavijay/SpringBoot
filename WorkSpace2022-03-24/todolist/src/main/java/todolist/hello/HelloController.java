package todolist.hello;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//	@RequestMapping("/hello")
//	public String sayHello() {
//		return "Hare Krishna!";
//	}

	@GetMapping(path = { "/hello", "/hello/{name}" })
	public String sayHello(@PathVariable(required = false, name = "name") String name,
			@RequestParam(required = false) Map<String, String> qparams,
			@RequestParam(required = false) String userName) {
		qparams.forEach((a, b) -> {
			System.out.println(String.format("%s -> %s", a, b));
		});
		System.out.println(name);
		System.out.println(userName);
		String retMsg = "Hare Krishna!";
		if (name != null)
			retMsg = name + " " + retMsg;
		return retMsg;
	}
}