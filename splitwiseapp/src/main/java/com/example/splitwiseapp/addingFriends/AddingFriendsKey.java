package com.example.splitwiseapp.addingFriends;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class AddingFriendsKey implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "group_id")
    private int groupId;

    // equals(), hashCode(), getters, setters
}
