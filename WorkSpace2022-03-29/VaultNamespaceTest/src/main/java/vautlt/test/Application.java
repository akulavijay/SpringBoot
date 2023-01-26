package vautlt.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.vault.core.VaultSysOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	@Autowired
	private VaultTemplate vaultTemplate;

	@RequestMapping("/vault-test")
	public String home() {
		System.out.println(vaultTemplate.toString());
		VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();
		VaultSysOperations sysOperations = vaultTemplate.opsForSys();
		String plainText = "This is a Plain Text";
		String ciphertext = transitOperations.encrypt("MyKey", plainText);
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
		return "Plain Text:"+plainText+" ciphertext:"+ciphertext;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}