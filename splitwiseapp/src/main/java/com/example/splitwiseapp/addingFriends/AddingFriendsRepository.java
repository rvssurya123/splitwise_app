package com.example.splitwiseapp.addingFriends;

import com.example.splitwiseapp.groups.Groups;
import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddingFriendsRepository extends JpaRepository<AddingFriends, Integer> {
    Optional<AddingFriends> findByUserIdAndGroupIdAndAddedBy(int userId, int groupId, int addedBy);
    List<AddingFriends> findAllByUserId(int userId);

}
