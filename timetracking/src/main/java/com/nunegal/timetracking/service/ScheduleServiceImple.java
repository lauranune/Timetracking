package com.nunegal.timetracking.service;

import com.nunegal.timetracking.entity.Schedule;
import com.nunegal.timetracking.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImple implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAll(){
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule findById(int id){
        return scheduleRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Schedule save(Schedule schedule){
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteById(int id){
        scheduleRepository.deleteById(id);
    }

}
