package com.example.splitwiseapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public void deleteUser(Integer id){
        usersRepository.deleteById(id);
    }

    public int updateUserDetails(int id, Map<String, String> userFields){
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // loops for the assign new updates
        for(Map.Entry<String, String> entry : userFields.entrySet()){
            String field = entry.getKey();
            String value = entry.getValue();

            if("userName".equals(field)){
                String lowerCaseValue = value.toLowerCase();
                user.setUserName(lowerCaseValue);
            } else if ("email".equals(field)) {
                String lowerCaseValue = value.toLowerCase();
                user.setEmail(lowerCaseValue);
            } else if ("password".equals(field)) {
                String encriptedPassword = passwordEncoder.encode(value);
                user.setPassword(encriptedPassword);
            }
        }
        usersRepository.save(user);
        return user.getUserId();
    }

    public Users getUserDetailsById(int id){
        Users userDetails = usersRepository.findById(id).orElse(null);
        return userDetails;
    }

}
