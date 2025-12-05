package com.example.splitwiseapp.groups;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity(name = "groups_table")
@Data
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;

    private String groupName;
    private int groupCreatedBy;
    @CreationTimestamp
    @Column(name = "group_created_at")
    private Date createdAt;
    @Column(name = "group_updated_at")
    private Date updatedAt;
}
