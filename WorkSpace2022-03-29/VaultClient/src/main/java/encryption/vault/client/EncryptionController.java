package encryption.vault.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {
	@RequestMapping("/encrypt/{keyIdentifier}")
	public String encrypt() {
		return "encrypt method called";
	}

	@RequestMapping("/decrypt/{keyIdentifier}")
	public String decrypt() {
		return "decrypt method called";
	}
}
