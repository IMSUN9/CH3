package com.ch3.controller;

import com.ch3.dto.*;
import com.ch3.entity.Schedule;
import com.ch3.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 속성
    private final ScheduleService scheduleService;

    // 생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 기능
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request) {

        Schedule savedSchedule = scheduleService.createSchedule(
                request.getTitle(),
                request.getContent(),
                request.getWriterName(),
                request.getPassword()
        );

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getWriterName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt()
        );
    }

    @GetMapping
    public List<GetScheduleResponse> getSchedules(@RequestParam(required = false) String writerName) {
        return scheduleService.getSchedules(writerName);
    }

    @GetMapping("/{scheduleId}")
    public GetScheduleResponse getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @PutMapping("/{scheduleId}")
    public GetScheduleResponse updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
    ) {
        return scheduleService.updateSchedule(
                scheduleId,
                request.getTitle(),
                request.getWriterName(),
                request.getPassword()
        );
    }

    @DeleteMapping("/{scheduleId}")
    public DeleteScheduleResponse deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ) {
        scheduleService.deleteSchedule(scheduleId, request.getPassword());
        return new DeleteScheduleResponse("일정 삭제 완료");
    }

}
