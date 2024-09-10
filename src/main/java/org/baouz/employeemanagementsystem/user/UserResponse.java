package org.baouz.employeemanagementsystem.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.baouz.employeemanagementsystem.department.Department;
import org.baouz.employeemanagementsystem.department.DepartmentResponse;


import java.time.LocalDate;
import java.time.LocalDateTime;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private LocalDate dob;

    private Gender gender;

    private Department department;
}
