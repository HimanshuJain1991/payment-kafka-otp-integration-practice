package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    //CREATE
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
       existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
       return userRepository.save(existingUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(int id){
       User user= userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+id));
       userRepository.delete(user);
    }
    public User findByName(String name){
        User user=userRepository.findByName(name);
        if(user==null){
            throw new RuntimeException("User not found with name:"+name);
        }
        return user;
    }
}
