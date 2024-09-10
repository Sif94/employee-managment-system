package org.baouz.employeemanagementsystem.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .dob(user.getDob())
                .email(user.getEmail())
                .gender(user.getGender())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .phone(user.getPhone())
                .department(user.getDepartment())
                .build();
    }

    public User toUser(UserRequest request) {
        return User
                .builder()
                .id(request.getId())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .gender(request.getGender())
                .dob(request.getDob())
                .phone(request.getPhone())
                .build();
    }
}
