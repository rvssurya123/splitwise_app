package com.example.splitwiseapp.friends;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "friends")
@Data
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_list_id")
    private int friendListId;

    private int userId;

    private int friendId;
}
