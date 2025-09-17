package com.example.splitwiseapp.friends;

import com.example.splitwiseapp.users.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FriendsRepository extends CrudRepository<Friends, Integer> {
//    Optional<Users> findByEmail(String email);
    Optional<Friends> findByUserIdAndFriendId(int userId, int friendId);

    // Assuming Friend entity has fields: userId1, userId2 representing the two friends
    //boolean existsByUserId1AndUserId2(Integer userId, Integer friendId);
    //boolean existsByUserIdAndFriendId(Integer userId, Integer friendId);


    // If friendship is bidirectional (order doesn't matter), define a query:
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Friends f WHERE " +
            "(f.userId = :userId1 AND f.friendId = :userId2) OR (f.userId = :userId2 AND f.friendId = :userId1)")
    boolean existsFriendshipBetween(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

}
