package com.example.splitwiseapp.friends;

import com.example.splitwiseapp.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    // API for add friends
    @PostMapping("/addfriend/{id}")
    public void addFriend(@PathVariable int id, @RequestBody Users users){
        friendsService.addFriend(id, users);
    }

    // API for Delete friend

}
