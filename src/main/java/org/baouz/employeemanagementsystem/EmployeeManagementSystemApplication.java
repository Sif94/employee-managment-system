package org.baouz.employeemanagementsystem;

import org.baouz.employeemanagementsystem.department.Department;
import org.baouz.employeemanagementsystem.department.DepartmentRepository;
import org.baouz.employeemanagementsystem.role.Role;
import org.baouz.employeemanagementsystem.role.RoleRepository;
import org.baouz.employeemanagementsystem.user.Gender;
import org.baouz.employeemanagementsystem.user.User;
import org.baouz.employeemanagementsystem.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAsync
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            RoleRepository roleRepository,
            UserRepository userRepository,
            DepartmentRepository departmentRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            Role admin = roleRepository.save(
                    Role
                            .builder()
                            .name("ADMIN")
                            .description("Admin role")
                            .build());
            Role employee = roleRepository.save(
                    Role
                            .builder()
                            .name("EMPLOYEE")
                            .description("Employee role")
                            .build());


            User emp1 = userRepository.save(User
                    .builder()
                    .firstname("Sif")
                    .lastname("Sif")
                    .email("sif@gmail.com")
                    .gender(Gender.MALE)
                    .dob(LocalDate.of(2000,6,24))
                    .accountLocked(false)
                    .enabled(true)
                    .roles(List.of(admin))
                    .password(passwordEncoder.encode("password"))
                    .phone("+213782963951")
                    .build());

            User emp2 = userRepository.save(User
                    .builder()
                    .firstname("Sifou")
                    .lastname("Sifou")
                    .email("sifou@gmail.com")
                    .gender(Gender.MALE)
                    .dob(LocalDate.of(2000, Month.JUNE, 24))
                    .accountLocked(false)
                    .enabled(true)
                    .roles(List.of(employee))
                    .password(passwordEncoder.encode("password"))
                    .phone("+213782963952")
                    .build());
            Department department = departmentRepository.save(Department
                    .builder()
                    .name("DSI")
                    .description("Department DSI")
                    .location("R+12")
                    .ceo(emp1)
                    .build());
            emp2.setDepartment(department);
            emp1.setDepartment(department);
            userRepository.save(emp1);
            userRepository.save(emp2);
        };

    }
}
