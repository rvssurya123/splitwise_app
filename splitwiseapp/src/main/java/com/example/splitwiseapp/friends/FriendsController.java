package com.example.splitwiseapp.friends;

import com.example.splitwiseapp.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/splitwise")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    // API for add friends
    //Inputs : Mail Id whom we are adding as friend, Pathvariable : who is owner
    @PostMapping("/addfriend/{id}")
    public void addFriend(@PathVariable int id, @RequestBody Users users){
        friendsService.addFriend(id, users);
    }

    // API for Delete friend
    //Inputs : Mail Id whom we are remove as friend, Pathvariable : who is owner
    @DeleteMapping("/deletefriend/{id}/{email}")
    public void deletefriend(@PathVariable int id, @PathVariable String email){
        friendsService.deleteFriend(id, email);
    }
}
