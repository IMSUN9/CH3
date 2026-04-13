package com.ch3.dto;

public class CreateScheduleRequest {

    // 속성
    private String title;
    private String content;
    private String writerName;
    private String password;

    // 생성자
    public CreateScheduleRequest() {

    }

    // 기능

    // getter

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getPassword() {
        return password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
