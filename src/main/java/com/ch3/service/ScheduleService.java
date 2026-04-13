package com.ch3.service;

import com.ch3.entity.Schedule;
import com.ch3.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    // 속성
    private final ScheduleRepository scheduleRepository;

    // 생성자
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 기능
    public Schedule createSchedule(String title, String content, String writerName, String password) {
        Schedule schedule = new Schedule(title, content, writerName, password);
        return scheduleRepository.save(schedule);
    }
}
