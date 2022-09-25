package ru.fominskiy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fominskiy.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
