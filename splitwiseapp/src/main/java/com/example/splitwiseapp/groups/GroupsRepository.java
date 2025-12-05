package com.example.splitwiseapp.groups;


import org.apache.logging.log4j.simple.internal.SimpleProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GroupsRepository extends CrudRepository<Groups, Integer> {
}
