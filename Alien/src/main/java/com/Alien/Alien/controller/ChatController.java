package com.Alien.Alien.controller;

import com.Alien.Alien.dto.ChatRequest;
import com.Alien.Alien.dto.ChatResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api") // All endpoints in this class will start with /api
public class ChatController {

    // Define the URL of your Python API
    private final String pythonApiUrl = "http://localhost:5001/chat";

    @PostMapping("/chat")
    public ChatResponse chatWithBot(@RequestBody ChatRequest request) {
        // Create a RestTemplate instance to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Call the Python API and get the response
        // Spring will automatically convert the JSON response into our ChatResponse object
        ChatResponse response = restTemplate.postForObject(
                pythonApiUrl,
                request,
                ChatResponse.class
        );

        return response;
    }

}