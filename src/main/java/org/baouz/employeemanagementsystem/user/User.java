package org.baouz.employeemanagementsystem.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.baouz.employeemanagementsystem.commun.BaseEntity;
import org.baouz.employeemanagementsystem.department.Department;
import org.baouz.employeemanagementsystem.role.Role;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name="users")
@DynamicUpdate
@DynamicInsert
public class User extends BaseEntity implements UserDetails, Principal {

    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(nullable = false)
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;
    @ManyToMany(fetch = EAGER)
    @JsonIgnore
    private List<Role> roles;
    @JsonIgnore
    private boolean enabled;
    @JsonIgnore
    private boolean accountLocked;

    @ManyToOne
    @JsonIgnore
    private Department department;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getName() {
        return email;
    }

    public String getFullName(){
        return firstname + " " + lastname;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }
}
