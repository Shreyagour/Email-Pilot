package com.email.writer.app;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmailGeneratorService {
	
	private final WebClient webClient;
	
	@Value("${gemini.api.url}")
	private String geminiApiUrl;
	
	@Value("${gemini.api.key}")
	private String geminiApiKey;
	
	public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

	public String generateEmailReply(EmailRequest emailRequest) {
		String prompt=buildPrompt(emailRequest);
		
		//crafting the request as per its structure
		
		Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                       Map.of("parts", new Object[]{
                               Map.of("text", prompt)
                       })
                }
        );
		
		
		System.out.println("Request Body: " + requestBody);
		
		//sending the request and handling the response
		String response=webClient.post()
				.uri(geminiApiUrl + geminiApiKey)
				.header("Content-Type","application/json")
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		System.out.println("Full Response: " + response);
		
		//extracting the response according to the one gemini sends as checked on postman
		//and returning it
		
		 return extractResponseContent(response);
	}
	
	private String extractResponseContent(String response) {
		
		
		try {
            ObjectMapper mapper = new ObjectMapper();//allows to handle json data (converting json->java object and vice versa)
            JsonNode rootNode = mapper.readTree(response);//turns json response to tree like structure
            return rootNode.path("candidates") //here we go inside the response structure to the "text" field where the actual reponse sent by api exists
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            return "Error processing request: " + e.getMessage();
        }
	}

	public String buildPrompt(EmailRequest emailRequest) {
		StringBuilder prompt=new StringBuilder();
		prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line ");
		if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()) {
			prompt.append("Use a").append(emailRequest.getTone()).append(" tone.");
		}
		prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
		return prompt.toString();
	}
}
