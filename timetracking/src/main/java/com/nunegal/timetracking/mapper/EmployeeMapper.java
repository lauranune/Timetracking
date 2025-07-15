package com.nunegal.timetracking.mapper;

import com.nunegal.timetracking.dto.EmployeeDto;
import com.nunegal.timetracking.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel= "spring")
public interface EmployeeMapper {

    @Mapping(target = "department.id", source = "departmentId")
    @Mapping(target = "rol.id", source = "rolId")
    @Mapping(target = "schedule.id", source = "scheduleId")
    Employee toEntity(EmployeeDto dto);

    List<EmployeeDto> toEmployeeDto(List<Employee> employees);

    void updateEmployee(@MappingTarget Employee employee, EmployeeDto dto);

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "departmentName", source = "department.name")
    @Mapping(target = "rolId", source = "rol.id")
    // @Mapping(target="rolName", source = "rol.name") -> Error
    @Mapping(target = "scheduleId", source = "schedule.id")
    static
        // @Mapping(target = "scheduleName", source="schedule.name") -> Error
    EmployeeDto toEmployeeDto(Employee employee) {
        return null;
    }
}
