package com.example.splitwiseapp.friends;

import com.example.splitwiseapp.users.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/splitwise")
@Tag(
        name = "Friends Management",
        description = "APIs to add and remove friends for a Splitwise user."
)
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    // API for add friends
    //Inputs : Mail Id whom we are adding as friend, Pathvariable : who is owner
    @Operation(
            summary = "Add friend",
            description = "Adds a new friend for the given user ID using the friend's email in the request body."
    )
    @PostMapping("/addfriend/{id}")
    public void addFriend(@PathVariable int id, @RequestBody Users users){
        friendsService.addFriend(id, users);
    }

    // API for Delete friend
    //Inputs : Mail Id whom we are remove as friend, Pathvariable : who is owner
    @Operation(
            summary = "Delete friend",
            description = "Removes an existing friend identified by email for the given user ID."
    )
    @DeleteMapping("/deletefriend/{id}/{email}")
    public void deletefriend(@PathVariable int id, @PathVariable String email){
        friendsService.deleteFriend(id, email);
    }
}
