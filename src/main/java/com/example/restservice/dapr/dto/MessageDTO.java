package com.example.restservice.dapr.dto;

public class MessageDTO {
    String data;

    public MessageDTO() {
    }

    public MessageDTO(String message) {
        this.data = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
