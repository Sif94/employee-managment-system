package org.baouz.employeemanagementsystem.department;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DepartmentRequest {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    private String location;
    @Positive
    private Long ceoId;
}
