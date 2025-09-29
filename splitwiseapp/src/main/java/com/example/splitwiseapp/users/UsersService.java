package com.example.splitwiseapp.users;

import com.example.splitwiseapp.usersDTOs.UpdateUserDetailsRequestDTO;
import com.example.splitwiseapp.usersDTOs.UserCreationRequestDTO;
import com.example.splitwiseapp.usersDTOs.UserCreationResponseDTO;
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

        newUser.setUserName(requestDTO.getUserName().toLowerCase());            // converted into lowercase
        newUser.setEmail(requestDTO.getEmail().toLowerCase());                // converted into lowercase
        newUser.setPassword(passwordEncoder.encode(requestDTO.getPassword()));     // encripted password to save in DB

        Users savedNewUser = usersRepository.save(newUser);   // saved into DB

        UserCreationResponseDTO responseDTO = new UserCreationResponseDTO();  // assigned required response varibles to userCreationResponseDTO
        responseDTO.setUserName(savedNewUser.getUserName());
        responseDTO.setUserId(savedNewUser.getUserId());

        return responseDTO;
    }

    // Logic in Service for delete user
    public void deleteUser(Integer id){usersRepository.deleteById(id);}

    // Logic in Service for update user
    public void updateUserDetails(int id, UpdateUserDetailsRequestDTO updateUserDetailsRequestDTO
    ){
        Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserName(updateUserDetailsRequestDTO.getUserName().toLowerCase());
        user.setEmail(updateUserDetailsRequestDTO.getEmail().toLowerCase());
        String encriptedPassword = passwordEncoder.encode(updateUserDetailsRequestDTO.getPassword());
        user.setPassword(encriptedPassword);

        usersRepository.save(user);
    }

    // Logic in Service for get user details by id
    public Users getUserDetailsById(int id){
        Users userDetails = usersRepository.findById(id).orElse(null);
        return userDetails;
    }
}
