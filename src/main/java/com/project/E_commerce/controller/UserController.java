package com.project.E_commerce.controller;

import com.project.E_commerce.dto.UserDTO;
import com.project.E_commerce.model.User;
import com.project.E_commerce.repositories.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> get(){
        return userRepository.findAll()
                .stream().map(user -> new UserDTO(user.getId(),user.getUsername(),user.getRole())).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id ){
        userRepository.deleteById(id);
        return "user with id : "+id+" deleted succesfully";
    }
}
