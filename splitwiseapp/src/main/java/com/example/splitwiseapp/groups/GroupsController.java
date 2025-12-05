package com.example.splitwiseapp.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/splitwise")
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    //api for Create groups
    //Inputs: groupName, Id who is creating group through path variable
    //Output: groupId
    @PostMapping("/creategroup/{id}")
    public int createGroup(@PathVariable int id, @RequestBody Groups groups){
        int groupId = groupsService.createGroup(id, groups);
        return groupId;
    }

    //delete group
    //Inputs: GroupId through path variable
    @DeleteMapping("/deletegroup/{id}")
    public void deleteGroup(@PathVariable int id){
        groupsService.deleteGroup(id);
    }

    //Change group details like name
    //Inputs: GroupId through path variable, name through requestBody
    @PatchMapping("/updategroupdetails/{id}")
    public String updateGroupDetails(@PathVariable int id, @RequestBody String groupName){
        groupsService.updateGroupDetails(id, groupName);
        return "GROUP NAME UPDATED";
    }
}
