package ru.fominskiy.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "Select u from User u"),
        @NamedQuery(name = "countAllUsers", query = "Select count(u) from User u"),
        @NamedQuery(name = "deleteUserById", query = "delete from User u where u.id = :id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 1024)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
