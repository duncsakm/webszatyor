package com.ivi.dyndns.webszatyor.repository;

import com.ivi.dyndns.webszatyor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

}
