package com.DigitalLibrary.DigitalLibrary.Service;

import com.DigitalLibrary.DigitalLibrary.Model.User;
import com.DigitalLibrary.DigitalLibrary.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAllBooksIssuedToUser(String userName) {
        User user=userRepository.findByUserName(userName);
        return userRepository.findById(user.getUserId()).stream().toList();
    }
}
