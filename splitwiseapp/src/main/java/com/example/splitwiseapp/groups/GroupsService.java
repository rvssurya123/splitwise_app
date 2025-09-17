package com.example.splitwiseapp.groups;

import com.example.splitwiseapp.exceptionMessage.UserNotFoundException;
import com.example.splitwiseapp.friends.FriendsService;
import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupsService {
    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public int createGroup(int id, Groups groups){
        Groups newGroup = new Groups();
        newGroup.setGroupCreatedBy(id);
        newGroup.setGroupName(groups.getGroupName());
        Groups savedGroup = groupsRepository.save(newGroup);
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

//    public void addMembersIntoGroup(int groupId, int userId, String mail) {
//        if(!userIsExist(userId)){
//            throw new UserNotFoundException("does not exist");
//        }
//        Optional<Users> users = usersRepository.findByEmail(mail);
//        if(!userIsExist(users.get().getUserId())){
//            throw new UserNotFoundException("does not exist");
//        }
//        if (groupsRepository.existsById(groupId)){
//            throw new UserNotFoundException("does not exist");
//        }
//
//
//
//
//    }
//    public boolean userIsExist(int userId){
//        return usersRepository.existsById(userId);
//    }
}
