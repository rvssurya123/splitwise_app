package com.example.splitwiseapp.users;

import com.example.splitwiseapp.usersDTOs.UpdateUserDetailsRequestDTO;
import com.example.splitwiseapp.usersDTOs.UserCreationRequestDTO;
import com.example.splitwiseapp.usersDTOs.UserCreationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Post Mapping call to add users or Create users
    //Inputs : UserName, eMail, Password
    // Output : UserName and UserId
    @PostMapping("/createuser")
    public ResponseEntity<UserCreationResponseDTO> createUser(@RequestBody UserCreationRequestDTO requestDTO){
        UserCreationResponseDTO savedUser = usersService.createUser(requestDTO);
        return ResponseEntity.ok(savedUser);
    }

    // Delete API for delete users
    // Input userId through path variable
    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable Integer id){
        usersService.deleteUser(id);
        return "User Deleted";
    }

    // Patch for update users if they want to change(userName, email, password)
    // Inputs : userName, eMail, password (required fields to change)
    @PatchMapping("/updateuserdetails/{id}")
    public void updateUserDetails(@PathVariable int id, @RequestBody UpdateUserDetailsRequestDTO updateUserDetailsRequestDTO){
        usersService.updateUserDetails(id, updateUserDetailsRequestDTO);
    }

    // Get details by userName
    // Input: userId through path variable
    @GetMapping("/getuserdetailsbyid/{id}")
    public ResponseEntity<Users> getUserDetailsById(@PathVariable int id){
        Users userDetails = usersService.getUserDetailsById(id);
        return ResponseEntity.ok(userDetails);
    }
}
