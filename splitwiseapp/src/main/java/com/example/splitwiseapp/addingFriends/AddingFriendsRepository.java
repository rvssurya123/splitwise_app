package com.example.splitwiseapp.addingFriends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddingFriendsRepository extends JpaRepository<AddingFriends, Integer> {
    Optional<AddingFriends> findByUserIdAndGroupIdAndAddedBy(int userId, int groupId, int addedBy);

}
