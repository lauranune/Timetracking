package com.nunegal.timetracking.service;

import com.nunegal.timetracking.entity.WorkingType;
import com.nunegal.timetracking.repository.WorkingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingTypeServiceImple implements WorkingTypeService {

    @Autowired
    private WorkingTypeRepository workingTypeRepository;

    @Override
    public List<WorkingType> findAll() {
        return workingTypeRepository.findAll();
    }

    @Override
    public WorkingType findById(int id) {
        return workingTypeRepository.findById(id)
                .orElse(null);
    }
}
