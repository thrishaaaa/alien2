package com.Alien.Alien.dto;

import java.util.List;

public class ChatResponse {
    private String main_reply;
    private List<Object> actionable_items;
    private String disclaimer;

    // Default constructor
    public ChatResponse() {}

    // Parameterized constructor
    public ChatResponse(String main_reply, List<Object> actionable_items, String disclaimer) {
        this.main_reply = main_reply;
        this.actionable_items = actionable_items;
        this.disclaimer = disclaimer;
    }

    // Getters and setters
    public String getMain_reply() {
        return main_reply;
    }

    public void setMain_reply(String main_reply) {
        this.main_reply = main_reply;
    }

    public List<Object> getActionable_items() {
        return actionable_items;
    }

    public void setActionable_items(List<Object> actionable_items) {
        this.actionable_items = actionable_items;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }
}

