package org.inu.nfcoffee.employee.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@Service
public class Web3Service {

    @Value("${node.server.url}")
    private String nodeServerUrl;

    public String mintAndTransfer(String recipient, String tokenURI) {
        RestTemplate restTemplate = new RestTemplate();

        String url = nodeServerUrl + "/api/mint-and-transfer";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("recipient", recipient);
        requestBody.put("tokenURI", tokenURI);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForObject(url, request, String.class);
    }
}
