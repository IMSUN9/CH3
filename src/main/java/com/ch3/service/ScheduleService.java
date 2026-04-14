package com.ch3.service;

import com.ch3.dto.GetScheduleResponse;
import com.ch3.entity.Schedule;
import com.ch3.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public List<GetScheduleResponse> getSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAllByOrderByUpdatedAtDesc();

        List<GetScheduleResponse> responseList = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            GetScheduleResponse response = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getWriterName(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );

            responseList.add(response);
        }
        return responseList;
    }

    public GetScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriterName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public GetScheduleResponse updateSchedule(Long scheduleId, String title, String writerName, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));

        if (!schedule.isPasswordMatch(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(title, writerName);

        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getWriterName(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다."));

        if (!schedule.isPasswordMatch(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);
    }

}
