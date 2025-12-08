package com.example.splitwiseapp.groups;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/splitwise")
@Tag(
        name = "Group Management",
        description = "APIs to create, update, and delete Splitwise groups."
)
public class GroupsController {

    @Autowired
    private GroupsService groupsService;

    //api for Create groups
    //Inputs: groupName, Id who is creating group through path variable
    //Output: groupId
    @Operation(
            summary = "Create new group",
            description = "Creates a new group for the given user ID using the provided group name, and returns the generated group ID."
    )
    @PostMapping("/creategroup/{id}")
    public int createGroup(@PathVariable int id, @RequestBody Groups groups){
        int groupId = groupsService.createGroup(id, groups);
        return groupId;
    }

    //Change group details like name
    //Inputs: GroupId through path variable, name through requestBody
    @Operation(
            summary = "Update group details",
            description = "Updates group information such as the group name for the specified group ID."
    )
    @PatchMapping("/updategroupdetails/{id}")
    public String updateGroupDetails(@PathVariable int id, @RequestBody String groupName){
        groupsService.updateGroupDetails(id, groupName);
        return "GROUP NAME UPDATED";
    }

    //delete group
    //Inputs: GroupId through path variable
    @Operation(
            summary = "Delete group",
            description = "Deletes the group identified by the given group ID."
    )
    @DeleteMapping("/deletegroup/{id}")
    public void deleteGroup(@PathVariable int id){
        groupsService.deleteGroup(id);
    }

}
