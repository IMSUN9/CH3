package com.ch3.dto;

public class UpdateScheduleRequest {

    // 속성
    private String title;
    private String writerName;
    private String password;

    // 생성자
    public UpdateScheduleRequest() {

    }

    // 기능

    // getter
    public String getTitle() {
        return title;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getPassword() {
        return password;
    }

    // setter

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
