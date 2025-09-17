package com.example.splitwiseapp.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    //api for Create groups
    @PostMapping("/creategroup/{id}")
    public int createGroup(@PathVariable int id, @RequestBody Groups groups){
        int groupId = groupsService.createGroup(id, groups);
        return groupId;
    }

    //delete group
    @DeleteMapping("/deletegroup/{id}")
    public void deleteGroup(@PathVariable int id){
        groupsService.deleteGroup(id);
    }

    @PatchMapping("/updategroupdetails/{id}")
    public String updateGroupDetails(@PathVariable int id, @RequestBody String groupName){
        groupsService.updateGroupDetails(id, groupName);
        return "GROUP NAME UPDATED";
    }

//    @PostMapping("/addmembersintogroup/{id}")
//    public String addMembersIntoGroup(@PathVariable int groupId, int userId, @RequestBody String mail){
//        groupsService.addMembersIntoGroup(groupId, userId, mail);
//        return "Member Added";
//    }

}
