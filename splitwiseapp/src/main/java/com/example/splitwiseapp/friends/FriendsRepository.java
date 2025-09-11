package com.example.splitwiseapp.friends;

import com.example.splitwiseapp.users.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FriendsRepository extends CrudRepository<Friends, Integer> {
//    Optional<Users> findByEmail(String email);
}
