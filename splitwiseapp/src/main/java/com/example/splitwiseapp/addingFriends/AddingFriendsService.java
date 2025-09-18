package com.example.splitwiseapp.addingFriends;

import com.example.splitwiseapp.exceptionMessage.UserNotFoundException;
import com.example.splitwiseapp.friends.Friends;
import com.example.splitwiseapp.friends.FriendsRepository;
import com.example.splitwiseapp.groups.GroupsRepository;
import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddingFriendsService {
    private final UsersRepository usersRepository;
    private final GroupsRepository groupsRepository;
    private final AddingFriendsRepository addingFriendsRepository;
    private final FriendsRepository friendsRepository;

    public AddingFriendsService(UsersRepository usersRepository,
                                GroupsRepository groupsRepository,
                                AddingFriendsRepository addingFriendsRepository,
                                FriendsRepository friendsRepository) {
        this.usersRepository = usersRepository;
        this.groupsRepository = groupsRepository;
        this.addingFriendsRepository = addingFriendsRepository;
        this.friendsRepository = friendsRepository;
    }

    public void addMembersIntoGroup(int groupId, int userId, String mail) {
        //Group admin validation
        if(!userIsExist(userId)){
            throw new UserNotFoundException("UserId does not exist");
        }
        //Group member validation
        Optional<Users> optionalUsers = usersRepository.findByEmail(mail);
        int groupMember = optionalUsers.get().getUserId();
        if(optionalUsers.isEmpty() ||!userIsExist(optionalUsers.get().getUserId())){
            throw new UserNotFoundException("who is added does not exist");
        }
        //Group validation
        if (!groupsRepository.existsById(groupId)){
            throw new UserNotFoundException("groupId does not exist");
        }
        //is group admin and member friends?
        if(!bothAreFriendsOrNot(userId, groupMember)){
            throw new UserNotFoundException("make them friend");
        }

        //After all validations adding group member into group
        AddingFriends addingFriends = new AddingFriends();
        addingFriends.setGroupId(groupId);
        addingFriends.setAddedBy(userId);
        addingFriends.setUserId(groupMember);

        addingFriendsRepository.save(addingFriends);

    }

    public void deleteMemberFromGroup(int groupId, int addedBy, String email) {
        Optional<Users> optionalUsers = usersRepository.findByEmail(email);
        if (optionalUsers.isEmpty()) {
            throw new UserNotFoundException("User not found for email: " + email);
        }

        int groupMember = optionalUsers.get().getUserId();
        int groupMemberCommonId = getGroupMemberCommonId(groupMember, groupId, addedBy);
        addingFriendsRepository.deleteById(groupMemberCommonId);
    }



    public int getGroupMemberCommonId(int userId, int groupId, int addedBy) {
        return addingFriendsRepository.findByUserIdAndGroupIdAndAddedBy(userId, groupId, addedBy)
                .orElseThrow(() -> new RuntimeException("Group member not found"))
                .getGroupMemberCommonId();
    }





    public boolean userIsExist(int userId){
        return usersRepository.existsById(userId);
    }

    public boolean bothAreFriendsOrNot(int userId, int groupMember){
        return friendsRepository.existsFriendshipBetween(userId, groupMember);
    }
}
