package com.nunegal.timetracking.service;

import com.nunegal.timetracking.entity.WorkingType;

import java.util.List;

public interface WorkingTypeService {
    public List<WorkingType> findAll();

    //    public WorkingType save(WorkingType workingType);
//
    public WorkingType findById(int id);
//
//    public WorkingType update(WorkingType workingType);
//
//    public void delete(int id);

}
