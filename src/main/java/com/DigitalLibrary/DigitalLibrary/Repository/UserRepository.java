package com.DigitalLibrary.DigitalLibrary.Repository;

import com.DigitalLibrary.DigitalLibrary.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}
