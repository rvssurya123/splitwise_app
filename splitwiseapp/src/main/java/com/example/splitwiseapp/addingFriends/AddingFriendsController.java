package com.example.splitwiseapp.addingFriends;

import com.example.splitwiseapp.groups.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddingFriendsController {
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private AddingFriendsService addingFriendsService;

    @PostMapping("/addmembersintogroup/{groupId}/{userId}")
    public String addMembersIntoGroup(@PathVariable Integer groupId, @PathVariable Integer userId, @RequestBody String mail){
        addingFriendsService.addMembersIntoGroup(groupId, userId, mail);
        return "Member Added";
    }

    @DeleteMapping("deletememberfromgroup")
    public String deleteMemberFromGroup(@PathVariable int groupId, @PathVariable int userId, @RequestBody String email){
        addingFriendsService.deleteMemberFromGroup(groupId, userId, email);
        return "Done";
    }
}
