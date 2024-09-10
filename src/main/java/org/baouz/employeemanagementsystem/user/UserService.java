package org.baouz.employeemanagementsystem.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.baouz.employeemanagementsystem.commun.PageResponse;
import org.baouz.employeemanagementsystem.commun.Utils;
import org.baouz.employeemanagementsystem.department.Department;
import org.baouz.employeemanagementsystem.department.DepartmentRepository;
import org.baouz.employeemanagementsystem.exceptions.DepartmentNoFoundException;
import org.baouz.employeemanagementsystem.role.Role;
import org.baouz.employeemanagementsystem.role.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.baouz.employeemanagementsystem.commun.Utils.copyNonNullProperties;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public PageResponse<UserResponse> findAllUsers(int page, int size) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        List<UserResponse> userResponses = userPage
                .getContent()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
        return PageResponse
                .<UserResponse>builder()
                .content(userResponses)
                .page(page)
                .totalElements(userPage.getTotalElements())
                .pageSize(size)
                .totalPages(userPage.getTotalPages())
                .isFirst(userPage.isFirst())
                .isLast(userPage.isLast())
                .build();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toUserResponse(user);
    }

    public UserResponse saveUser(@Valid UserRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new DepartmentNoFoundException("Department not found"));
        User user = userMapper.toUser(request);
        List<Role> roles = new ArrayList<>();
        request.getRolesId().forEach(roleId -> {
            try {
                var role = roleRepository.findById(roleId)
                        .orElseThrow(() -> new RoleNotFoundException("Role not found"));
                roles.add(role);
            } catch (RoleNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        user.setDepartment(department);
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public UserResponse updateUser(Long id, UserRequest request) {
        User savedUser = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );
        request.setId(savedUser.getId());
        copyNonNullProperties(request, savedUser);

        return userMapper.toUserResponse(userRepository.save(savedUser));
    }
}
