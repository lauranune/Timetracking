package com.nunegal.timetracking.mapper;

import com.nunegal.timetracking.dto.ScheduleDto;
import com.nunegal.timetracking.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(target = "workingType.id", source = "workingTypeId")
    @Mapping(target = "employee.id", source = "employeeId")
    Schedule toEntity(ScheduleDto dto);

    @Mapping(target = "workingTypeId", source = "workingType.id")
    @Mapping(target = "workingTypeName", source = "workingType.name")
    @Mapping(target = "employeeId", source = "employee.id")
    ScheduleDto toScheduleDto(Schedule schedule);
}