package com.nunegal.timetracking.mapper;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ScheduleMapper.class})
public interface EmployeeMapper {

    @Mapping(target = "department.id", source = "departmentId")
    @Mapping(target = "rol.id", source = "rolId")
    @Mapping(target = "workingType.id", source = "workingTypeId")
    @Mapping(target = "schedules", source = "schedules")
    Employee toEntity(EmployeeDto dto);

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "rolId", source = "rol.id")
    @Mapping(target = "rolType", source = "rol.type")
    @Mapping(target = "workingTypeId", source = "workingType.id")
    @Mapping(target = "workingTypeName", source = "workingType.name")
    @Mapping(target = "schedules", source = "schedules")
    @Mapping(target = "confirmPassword", ignore = true)
    EmployeeDto toEmployeeDto(Employee employee);

    List<EmployeeDto> toEmployeeDto(List<Employee> employees);

    @Mapping(target = "department.id", source = "departmentId")
    @Mapping(target = "rol.id", source = "rolId")
    @Mapping(target = "workingType.id", source = "workingTypeId")
    @Mapping(target = "schedules", source = "schedules")
    void updateEmployee(@MappingTarget Employee employee, EmployeeDto dto);
}