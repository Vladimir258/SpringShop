package ru.fominskiy.repositories;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.fominskiy.entities.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {

    Page<User> findAllByUsernameLike(String usernameFilter, Pageable pageable);

    @Query("select u from User u join fetch u.roles where u.username = :username")
    Optional<User> findByUsername(String username);
}
