package com.example.splitwiseapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Service for new user creation
    public int createUser(Users user){
        String rawPassword = user.getPassword();
        String encriptedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encriptedPassword);

        //Converting username and email into lowercare
        String givenUserName = user.getUserName();
        String lowerCaseUserName = givenUserName.toLowerCase();
        user.setUserName(lowerCaseUserName);

        String givenEmail = user.getEmail();
        String lowerCaseEmail = givenEmail.toLowerCase();
        user.setEmail(lowerCaseEmail);

        Users savedUser = usersRepository.save(user);
        return savedUser.getUserId();
    }

}
