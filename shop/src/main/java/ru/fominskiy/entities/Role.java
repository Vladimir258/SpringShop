package ru.fominskiy.entities;

import lombok.Data;
import javax.persistence.*;

// Модель для хранения ролей
@Entity //
@Data //
@Table(name = "roles") //
public class Role { //
    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "id") //
    private Long id; //

    @Column(name = "name") //
    private String name; //
}
