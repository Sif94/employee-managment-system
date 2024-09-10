package org.baouz.employeemanagementsystem.department;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.baouz.employeemanagementsystem.exceptions.DepartmentNoFoundException;
import org.baouz.employeemanagementsystem.user.User;
import org.baouz.employeemanagementsystem.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.baouz.employeemanagementsystem.commun.Utils.copyNonNullProperties;


@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;
    private final UserRepository userRepository;

    public List<DepartmentResponse> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments
                .stream()
                .map(departmentMapper::toDepartmentResponse)
                .toList();
    }

    public DepartmentResponse findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNoFoundException("Department not found"));
        return departmentMapper.toDepartmentResponse(department);
    }

    public DepartmentResponse saveDepartment(DepartmentRequest request) {
        User ceo = userRepository.findById(request.getCeoId())
                .orElseThrow(() -> new UsernameNotFoundException("Ceo not found"));
        Department department = departmentMapper.toDepartment(request);
        department.setCeo(ceo);
        return departmentMapper.toDepartmentResponse(departmentRepository.save(department));
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department savedDepartment = departmentRepository.findById(id).orElseThrow(
                () -> new DepartmentNoFoundException("Department not found")
        );
        request.setId(savedDepartment.getId());
        copyNonNullProperties(request, savedDepartment);

        if (request.getCeoId() != null) {
            User ceo = userRepository.findById(request.getCeoId())
                    .orElseThrow(() -> new UsernameNotFoundException("Ceo not found"));
            savedDepartment.setCeo(ceo);
        }
        return departmentMapper
                .toDepartmentResponse(departmentRepository.save(savedDepartment));
    }

    public void deleteById(Long id) {
        var department = departmentRepository.findById(id).orElseThrow(
                () -> new DepartmentNoFoundException("Department not found")
        );
        departmentRepository.deleteById(id);
    }
}
