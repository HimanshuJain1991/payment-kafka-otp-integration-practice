package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //CREATE
    public User save(User user){
        return userRepository.save(user);
    }

    public User getById(int id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not found id:"+id));

    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User update(int id ,User newUser){
       User  existingUser= userRepository.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
       existingUser.setName(newUser.getName());
       return userRepository.save(existingUser);
    }

    public void delete(int id){
       User user= userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
       userRepository.delete(user);
    }
}
