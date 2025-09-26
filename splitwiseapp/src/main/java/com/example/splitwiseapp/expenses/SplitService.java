package com.example.splitwiseapp.expenses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SplitService {
    @Autowired
    private SplitRepository splitRepository;

}
