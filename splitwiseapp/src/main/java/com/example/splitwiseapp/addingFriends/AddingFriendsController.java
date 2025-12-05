package com.example.splitwiseapp.addingFriends;

import com.example.splitwiseapp.groups.Groups;
import com.example.splitwiseapp.groups.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/splitwise")
public class AddingFriendsController {
    @Autowired
    private GroupsService groupsService;
    @Autowired
    private AddingFriendsService addingFriendsService;

    //Adding group member
    //Inputs {groupId:where to add, userId: Who is adding, email: new group member}
    @PostMapping("/addmembersintogroup/{groupId}/{userId}")
    public String addMembersIntoGroup(@PathVariable Integer groupId, @PathVariable Integer userId, @RequestBody String mail){
        addingFriendsService.addMembersIntoGroup(groupId, userId, mail);
        return "Member Added";
    }

    //Remove group member from group
    //Inputs {groupId:where to add, userId: Who is removing, email: remove group member}
    @DeleteMapping("deletememberfromgroup/{groupId}/{userId}")
    public String deleteMemberFromGroup(@PathVariable int groupId, @PathVariable int userId, @RequestBody String email){
        addingFriendsService.deleteMemberFromGroup(groupId, userId, email);
        return "Done";
    }

    // To get all the groups from userId
    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/groups/{userId}")
    public List<Groups> getAllGroupsByUserId(@PathVariable int userId){
        return addingFriendsService.getAllGroupsByUserId(userId);
    }
}
