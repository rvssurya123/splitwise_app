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

        int getFrienduserIdByEmail = Math.toIntExact(getUserIdByEmail(users.getEmail()));

        Friends savedFriend = new Friends();
        savedFriend.setUserId(id);
        savedFriend.setFriendId(getFrienduserIdByEmail);
        friendsRepository.save(savedFriend);
    }

    public Long getUserIdByEmail(String email) {
        Optional<Users> userOptional = usersRepository.findByEmail(email);

            return (long) userOptional.get().getUserId();

    }
}
