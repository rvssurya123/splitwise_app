package com.example.splitwiseapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Post Mapping call to add users or Create users
    @PostMapping("/createuser")
    public int createUser(@RequestBody Users user){
        int id = usersService.createUser(user);
        return id;
    }
}
