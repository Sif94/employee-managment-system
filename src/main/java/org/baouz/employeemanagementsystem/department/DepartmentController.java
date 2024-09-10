package org.baouz.employeemanagementsystem.department;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
@Tag(name = "Department")
public class DepartmentController {
    private final DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @RequestBody @Valid DepartmentRequest departmentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.saveDepartment(departmentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequest request
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.updateDepartment(id,request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
    }
}
