package com.ch3.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateScheduleResponse {

    // 속성
    private Long id;
    private String title;
    private String content;
    private String writerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public CreateScheduleResponse(Long id, String title, String content, String writerName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 기능

    // getter

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriterName() {
        return writerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
