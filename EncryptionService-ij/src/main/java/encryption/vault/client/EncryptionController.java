package encryption.vault.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.VaultTransitOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;
import org.springframework.vault.support.VaultDecryptionResult;
import org.springframework.vault.support.VaultEncryptionResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {
	@Autowired
	private VaultTemplate vaultTemplate;

	@PostMapping("/encrypt/{keyIdentifier}")
	public Map<String, Object> encrypt(@PathVariable String keyIdentifier, @RequestBody Map<String, String> inputData) {
		VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();
		List<Plaintext> batch = new ArrayList<Plaintext>();
		for (String value : inputData.values()) 
			batch.add( Plaintext.of(value));
		List<VaultEncryptionResult> cipherLists = transitOperations.encrypt(keyIdentifier, batch);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int i = 0;
		for (String key : inputData.keySet())
			returnMap.put(key, cipherLists.get(i++).get().getCiphertext());
		return returnMap;
	}

	@PostMapping("/decrypt/{keyIdentifier}")
	public Map<String, Object> decrypt(@PathVariable String keyIdentifier, @RequestBody Map<String, String> inputData) {
		VaultTransitOperations transitOperations = vaultTemplate.opsForTransit();
		List<Ciphertext> cipherBatch = new ArrayList<Ciphertext>();
		for (String value : inputData.values()) 
			cipherBatch.add(Ciphertext.of(value));
		List<VaultDecryptionResult> decyptBatch = transitOperations.decrypt(keyIdentifier, cipherBatch);
		Map<String, Object> returnMap = new HashMap<String, Object>();
		int i = 0;
		for (String key : inputData.keySet())
			returnMap.put(key, decyptBatch.get(i++).getAsString());
		return returnMap;
	}
}
