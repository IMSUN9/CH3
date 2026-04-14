package com.ch3.repository;

import com.ch3.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findAllByOrderByUpdatedAtDesc();

    List<Schedule> findAllByWriterNameOrderByUpdatedAtDesc(String writerName);
}
