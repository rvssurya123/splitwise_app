package com.example.splitwiseapp.expenses;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SplitRepository extends CrudRepository<Split, Integer> {
    List<Split> findByGroupIdAndUserId(int groupId, int userId);
    List<Split> findByUserId(int userId);
}
