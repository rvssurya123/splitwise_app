package com.example.splitwiseapp.users;

import com.example.splitwiseapp.usersDTOs.UpdateUserDetailsRequestDTO;
import com.example.splitwiseapp.usersDTOs.UserCreationRequestDTO;
import com.example.splitwiseapp.usersDTOs.UserCreationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/splitwise")
@Tag(
        name = "User Management",
        description = "APIs to create, read, update, and delete Splitwise users."
)
public class UsersController {

    @Autowired
    private UsersService usersService;

    // Get details by userName
    // Input: userId through path variable
    @Operation(
            summary = "Get user details by ID",
            description = "Fetches full details of a user identified by the given ID."
    )
    @GetMapping("/getuserdetailsbyid/{id}")
    public ResponseEntity<Users> getUserDetailsById(@PathVariable int id){
        Users userDetails = usersService.getUserDetailsById(id);
        return ResponseEntity.ok(userDetails);
    }

    // Post Mapping call to add users or Create users
    //Inputs : UserName, eMail, Password
    // Output : UserName and UserId
    @Operation(
            summary = "Create a new user",
            description = "Creates a Splitwise user with username, email, and password, and returns the generated user ID and username."
    )
    @PostMapping("/createuser")
    public ResponseEntity<UserCreationResponseDTO> createUser(@RequestBody UserCreationRequestDTO requestDTO){
        UserCreationResponseDTO savedUser = usersService.createUser(requestDTO);
        return ResponseEntity.ok(savedUser);
    }

    // Patch for update users if they want to change(userName, email, password)
    // Inputs : userName, eMail, password (required fields to change)
    @Operation(
            summary = "Update user details",
            description = "Updates one or more profile fields (username, email, password) for the user with the given ID."
    )
    @PatchMapping("/updateuserdetails/{id}")
    public void updateUserDetails(@PathVariable int id, @RequestBody UpdateUserDetailsRequestDTO updateUserDetailsRequestDTO){
        usersService.updateUserDetails(id, updateUserDetailsRequestDTO);
    }

    // Delete API for delete users
    // Input userId through path variable
    @Operation(
            summary = "Delete a user",
            description = "Deletes an existing user identified by the user ID in the path."
    )
    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable Integer id){
        usersService.deleteUser(id);
        return "User Deleted";
    }

}
