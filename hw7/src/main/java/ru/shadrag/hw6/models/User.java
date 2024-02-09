package ru.shadrag.hw6.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.shadrag.hw6.components.Role;

import java.util.List;
@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    public User() {
        this.role = Role.ROLE_USER;
    }

    //region ПОЛЯ
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Task> userTasksList;

    @Column(name = "role")
    private Role role;
    //endregion
}
