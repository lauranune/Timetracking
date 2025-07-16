package com.nunegal.timetracking.service;

import com.nunegal.timetracking.entity.Department;

import java.util.List;

public interface DepartmentService {
    public Department save(Department department);
    public List<Department> findAll();
    public Department findById(int id);
    public Department update(Department department);
    public void delete(int id);
}
