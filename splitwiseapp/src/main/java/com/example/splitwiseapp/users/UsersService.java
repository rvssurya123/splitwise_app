package com.example.splitwiseapp.users;

import com.example.splitwiseapp.UsersDTOs.UserCreationRequestDTO;
import com.example.splitwiseapp.UsersDTOs.UserCreationResponseDTO;
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

    // Logic in Service for new user creation
    public UserCreationResponseDTO createUser(UserCreationRequestDTO requestDTO){
        Users newUser = new Users();  //created user object

        String lowerCaseName = requestDTO.getUserName().toLowerCase();
        newUser.setUserName(lowerCaseName);            // converted into lowercase

        String lowerCaseMail = requestDTO.getEmail().toLowerCase();
        newUser.setEmail(lowerCaseMail);                // converted into lowercase

        String encriptedPassword = passwordEncoder.encode(requestDTO.getPassword());    // encripted password to save in DB

        newUser.setPassword(encriptedPassword);

        Users savedNewUser = usersRepository.save(newUser);   // saved into DB

        UserCreationResponseDTO responseDTO = new UserCreationResponseDTO();  // assigned required response varibles to userCreationResponseDTO
        responseDTO.setUserName(savedNewUser.getUserName());
        responseDTO.setUserId(savedNewUser.getUserId());

        return responseDTO;
    }



    // Logic in Service for delete user
    public void deleteUser(Integer id){usersRepository.deleteById(id);}

    // Logic in Service for update user
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

    // Logic in Service for get user details by id
    public Users getUserDetailsById(int id){
        Users userDetails = usersRepository.findById(id).orElse(null);
        return userDetails;
    }

}
