package org.baouz.employeemanagementsystem.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.baouz.employeemanagementsystem.user.User;
import org.baouz.employeemanagementsystem.user.UserResponse;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private List<User> users;
    private User ceo;
}
