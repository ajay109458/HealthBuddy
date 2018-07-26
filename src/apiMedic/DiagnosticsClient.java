package apiMedic;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import apiMedicModel.AccessToken;

public class DiagnosticsClient {

	private AccessToken token;
	private String language;
	private String healthServiceUrl;
	
	public static void main(String[] args) {
		
		try {
			loadToken("Ajay458", "Xajay@458", "https://authservice.priaid.ch/login");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void loadToken(String username, String password, String url) throws Exception {

		SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), "HmacMD5");

		String computedHashString = "";
		try {
			Mac mac = Mac.getInstance("HmacMD5");
			mac.init(keySpec);
			byte[] result = mac.doFinal(url.getBytes());

			computedHashString = Base64.getEncoder().encodeToString(result);

			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Can not create token (NoSuchAlgorithmException)");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Can not create token (InvalidKeyException)");
		}

		Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(url);
		Builder request = resource.request();

		request.accept(MediaType.APPLICATION_JSON);

		System.out.println("Bearer " + username + ":" + computedHashString);
		
		request.header("Authorization", "Bearer " + username + ":" + computedHashString);

		String empty = "";
		Response response = request.post(Entity.entity(empty, MediaType.APPLICATION_JSON));
		
		System.out.println(response);
	}
}
