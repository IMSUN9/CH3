package com.ch3.dto;

public class DeleteScheduleResponse {

    // 속성
    private String message;

    // 생성자
    public DeleteScheduleResponse(String message) {
        this.message = message;
    }

    // 기능

    // getter
    public String getMessage() {
        return message;
    }

}
