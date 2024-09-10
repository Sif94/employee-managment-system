package org.baouz.employeemanagementsystem.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.baouz.employeemanagementsystem.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "departments")
public class Department {

    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    private String location;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<User> users;
    @OneToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User ceo;
}
