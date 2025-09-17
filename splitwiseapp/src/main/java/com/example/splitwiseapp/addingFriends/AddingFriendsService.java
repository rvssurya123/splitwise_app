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
        if(!userIsExist(userId)){
            throw new UserNotFoundException("UserId does not exist");
        }
        Optional<Users> optionalUsers = usersRepository.findByEmail(mail);
        int groupMember = optionalUsers.get().getUserId();
        if(optionalUsers.isEmpty() ||!userIsExist(optionalUsers.get().getUserId())){
            throw new UserNotFoundException("who is added does not exist");
        }
        if (!groupsRepository.existsById(groupId)){
            throw new UserNotFoundException("groupId does not exist");
        }
        if(!bothAreFriendsOrNot(userId, groupMember)){
            throw new UserNotFoundException("make them friend");
        }

        AddingFriendsKey key = new AddingFriendsKey();
        key.setGroupId(groupId);
        key.setUserId(optionalUsers.get().getUserId());

        AddingFriends addingFriends = new AddingFriends();
        addingFriends.setId(key);
        addingFriends.setAddedBy(userId);

        addingFriendsRepository.save(addingFriends);


    }

    public void deleteMemberFromGroup(int groupId, int userId, String mail){
        
    }



    public boolean userIsExist(int userId){
        return usersRepository.existsById(userId);
    }

    public boolean bothAreFriendsOrNot(int userId, int groupMember){
        return friendsRepository.existsFriendshipBetween(userId, groupMember);
    }
}
