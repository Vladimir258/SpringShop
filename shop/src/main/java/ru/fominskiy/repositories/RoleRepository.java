package ru.fominskiy.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.fominskiy.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
