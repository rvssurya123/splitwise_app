package com.example.splitwiseapp.addingFriends;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "group_members")
public class AddingFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_members_common_id")
    private int groupMemberCommonId;
    @Column(name = "user_id")
    private int userId;  //whom to add
    @Column(name = "group_id")
    private int groupId;  // where to add
    @Column(name = "added_by")
    private int addedBy;   // who is adding
    @CreationTimestamp
    private Date joinedAt;
}
