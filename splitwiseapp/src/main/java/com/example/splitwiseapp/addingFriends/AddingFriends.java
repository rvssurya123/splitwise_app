package com.example.splitwiseapp.addingFriends;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity(name = "group_members")
@Data
public class AddingFriends {

    @EmbeddedId
    private AddingFriendsKey id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private int userId;  //whom to add
    @Column(name = "group_id", insertable = false, updatable = false)
    private int groupId;  // where to add
    @Column(name = "added_by")
    private int addedBy;   // who is adding
    @CreationTimestamp
    private Date joinedAt;
}
