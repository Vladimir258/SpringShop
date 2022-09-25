package ru.fominskiy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fominskiy.entities.Role;
import ru.fominskiy.repositories.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
       return roleRepository.findAll();
    }
}
