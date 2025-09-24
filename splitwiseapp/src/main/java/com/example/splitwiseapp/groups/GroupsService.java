package com.example.splitwiseapp.groups;

import com.example.splitwiseapp.addingFriends.AddingFriends;
import com.example.splitwiseapp.addingFriends.AddingFriendsRepository;
import com.example.splitwiseapp.exceptionMessage.UserNotFoundException;
import com.example.splitwiseapp.friends.FriendsService;
import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupsService {
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AddingFriendsRepository addingFriendsRepository;

    public int createGroup(int id, Groups groups){
        Groups newGroup = new Groups();
        newGroup.setGroupCreatedBy(id);
        newGroup.setGroupName(groups.getGroupName());
        Groups savedGroup = groupsRepository.save(newGroup);

        //When group id is creating the only adding into members table user who created group
        newGroup.getGroupId();
        AddingFriends groupMember = new AddingFriends();
        groupMember.setGroupId(newGroup.getGroupId());
        groupMember.setUserId(id);
        groupMember.setAddedBy(id);
        addingFriendsRepository.save(groupMember);
        return savedGroup.getGroupId();
    }

    public void deleteGroup(int groupId){
        groupsRepository.deleteById(groupId);
    }

    public void updateGroupDetails(int id, String groupName) {
        Groups newGroup = new Groups();
        newGroup = groupsRepository.findById(id).orElse(null);
        newGroup.setGroupName(groupName);
        Groups savedGroup = groupsRepository.save(newGroup);
    }
}
