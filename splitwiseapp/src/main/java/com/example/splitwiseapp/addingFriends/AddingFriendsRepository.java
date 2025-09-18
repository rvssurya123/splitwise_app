package com.example.splitwiseapp.addingFriends;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddingFriendsRepository extends JpaRepository<AddingFriends, Integer> {
    Optional<AddingFriends> findByUserIdAndGroupIdAndAddedBy(int userId, int groupId, int addedBy);

}
