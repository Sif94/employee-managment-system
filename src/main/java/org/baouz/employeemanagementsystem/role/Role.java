package org.baouz.employeemanagementsystem.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.baouz.employeemanagementsystem.commun.BaseEntity;
import org.baouz.employeemanagementsystem.user.User;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    private String description;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

}
