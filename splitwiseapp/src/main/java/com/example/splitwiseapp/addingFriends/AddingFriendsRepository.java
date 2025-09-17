package com.example.splitwiseapp.addingFriends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddingFriendsRepository extends JpaRepository<AddingFriends, Integer> {
}
