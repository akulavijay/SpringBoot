package encryption;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultSysOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private VaultTemplate vaultTemplate;
    
	@Value("${spring.profiles.active}")
    private String vaultEnv;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	///v1/transit/integ/dev/encrypt/
	public void run(String... strings) throws Exception {
		System.out.println(vaultTemplate.toString());		
		System.out.println("ZZZ");
		System.out.println(vaultEnv);
		VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();
		VaultSysOperations sysOperations = vaultTemplate.opsForSys();
		String ciphertext = transitOperations.encrypt("MyKey", "Secure message");
		System.out.println("Encrypted value");
		System.out.println("-------------------------------");
		System.out.println(ciphertext);
		System.out.println("-------------------------------");
		System.out.println();
		transitOperations = vaultTemplate.opsForTransit("transit/integ/dev");
		sysOperations = vaultTemplate.opsForSys();
		ciphertext = transitOperations.encrypt("MyKey", "Secure message");
		System.out.println("Encrypted value");
		System.out.println("-------------------------------");
		System.out.println(ciphertext);
		System.out.println("-------------------------------");
		System.out.println();
		// Decrypt

		String plaintext = transitOperations.decrypt("MyKey", ciphertext);
		System.out.println("Decrypted value");
		System.out.println("-------------------------------");
		System.out.println(plaintext);
		System.out.println("-------------------------------");
		System.out.println();
		System.exit(0);
	}

	public static String toStringRecursive(Object o) throws Exception {

		if (o == null)
			return "null";

		if (LEAVES.contains(o.getClass()))
			return o.toString();

		StringBuilder sb = new StringBuilder();
		sb.append(o.getClass().getSimpleName()).append(": [");
		for (Field f : o.getClass().getDeclaredFields()) {
			if (Modifier.isStatic(f.getModifiers()))
				continue;
			f.setAccessible(true);
			sb.append(f.getName()).append(": ");
			sb.append(toStringRecursive(f.get(o))).append(" ");
		}
		sb.append("]");
		return sb.toString();
	}

	private static final List LEAVES = Arrays.asList(Boolean.class, Character.class, Byte.class, Short.class,
			Integer.class, Long.class, Float.class, Double.class, Void.class, String.class);
}