package com.example.splitwiseapp.friends;

import com.example.splitwiseapp.users.Users;
import com.example.splitwiseapp.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendsService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private FriendsRepository friendsRepository;

    public void addFriend(int id, Users users) {
        int friendUserId = Math.toIntExact(getUserIdByEmail(users.getEmail()));
        int userIdToSave = Math.min(id, friendUserId);
        int friendIdToSave = Math.max(id, friendUserId);

        Friends savedFriend = new Friends();
        savedFriend.setUserId(userIdToSave);
        savedFriend.setFriendId(friendIdToSave);

        friendsRepository.save(savedFriend);
    }

    public Long getUserIdByEmail(String email) {
        Optional<Users> userOptional = usersRepository.findByEmail(email);

            return (long) userOptional.get().getUserId();

    }

    public void deleteFriend(int id, String email ) {

        int friendUserId = Math.toIntExact(getUserIdByEmail(email));

        int userIdToDelete = Math.min(id, friendUserId);
        int friendIdToDelete = Math.max(id, friendUserId);

        int friendListId = getFriendListId(userIdToDelete, friendIdToDelete);
        friendsRepository.deleteById(friendListId);
//        int ownersId = id;
//        int friendId = Math.toIntExact(getUserIdByEmail(email));
//        int friendListId = getFriendListId(ownersId, friendId);
//        friendsRepository.deleteById(friendListId);

    }

    public int getFriendListId(int userId, int friendId) {
        Optional<Friends> friendOpt = friendsRepository.findByUserIdAndFriendId(userId, friendId);
        if (friendOpt.isPresent()) {
            return friendOpt.get().getFriendListId();
        } else {
            throw new RuntimeException("Friend relationship not found");
        }
    }
}
