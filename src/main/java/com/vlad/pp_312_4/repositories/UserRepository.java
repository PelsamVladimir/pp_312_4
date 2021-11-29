package com.vlad.pp_312_4.repositories;

import com.vlad.pp_312_4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    User findByName(String username);
}
