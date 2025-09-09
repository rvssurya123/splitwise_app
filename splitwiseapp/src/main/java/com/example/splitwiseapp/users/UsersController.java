package com.example.splitwiseapp.users;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // Delete API for delete users
    @DeleteMapping("/deleteuser/{id}")
    public void deleteUser(@PathVariable Integer id){
        usersService.deleteUser(id);
    }

    // Patch for update users if they want to change(userName, email, password)
    @PatchMapping("/updateuserdetails/{id}")
    public void updateUserDetails(@PathVariable int id, @RequestBody Map<String, String> user){
        int updatedUser = usersService.updateUserDetails(id, user);

    }

    // Get details by userName
    @GetMapping("/getuserdetailsbyid/{id}")
    public ResponseEntity<Users> getUserDetailsById(@PathVariable int id){
        Users userDetails = usersService.getUserDetailsById(id);
        return ResponseEntity.ok(userDetails);
    }
}
