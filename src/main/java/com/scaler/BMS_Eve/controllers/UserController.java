package com.scaler.BMS_Eve.controllers;

import com.scaler.BMS_Eve.DTOs.ResponseStatus;
import com.scaler.BMS_Eve.DTOs.UserSignUpRequestDTO;
import com.scaler.BMS_Eve.DTOs.UserSignUpResponseDTO;
import com.scaler.BMS_Eve.models.BookingStatus;
import com.scaler.BMS_Eve.models.User;
import com.scaler.BMS_Eve.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Controller
public class UserController {
    @Autowired
    UserService userService;
    public UserSignUpResponseDTO signUp(UserSignUpRequestDTO requestDTO){
        UserSignUpResponseDTO responseDTO = new UserSignUpResponseDTO();
        try{
            User user = userService.signup(
                    requestDTO.getName(),
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
            responseDTO.setUserId(user.getId());
            responseDTO.setMessage("User sign up succesfull");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            System.out.println("User sign up failed! - " + e.getMessage());
            responseDTO.setMessage("User sign up failed");
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
}
