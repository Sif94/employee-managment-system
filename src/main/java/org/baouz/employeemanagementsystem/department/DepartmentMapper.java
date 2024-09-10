package org.baouz.employeemanagementsystem.department;

import lombok.RequiredArgsConstructor;
import org.baouz.employeemanagementsystem.user.UserMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentMapper {

    private final UserMapper userMapper;

    public DepartmentResponse toDepartmentResponse(Department department) {
        return DepartmentResponse
                .builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .ceo(department.getCeo())
                .location(department.getLocation())
                .users(department.getUsers())
                .build();
    }

    public Department toDepartment(DepartmentRequest departmentRequest) {
        return Department
                .builder()
                .location(departmentRequest.getLocation())
                .name(departmentRequest.getName())
                .description(departmentRequest.getDescription())
                .build();
    }
}
