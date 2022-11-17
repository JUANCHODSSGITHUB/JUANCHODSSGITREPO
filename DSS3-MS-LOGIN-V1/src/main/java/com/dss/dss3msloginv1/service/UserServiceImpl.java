package com.dss.dss3msloginv1.service;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.util.TokenUtil;
import com.dss.dss3msloginv1.util.UserDTOChecker;
import com.dss.dss3msloginv1.util.UserDTOMapper;
import com.dss.dss3msloginv1.entity.User;
import com.dss.dss3msloginv1.exception.AdminAlreadyExistsException;
import com.dss.dss3msloginv1.exception.InvalidInputException;
import com.dss.dss3msloginv1.exception.LoginFailedException;
import com.dss.dss3msloginv1.exception.UserNotFoundException;
import com.dss.dss3msloginv1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;


    @Override
    public String addUser(UserDTO user) {
        String responseMessage = null;


        if (UserDTOChecker.validateUserDTO(user).isEmpty() && UserDTOChecker.validateUserDTOPassword(user.getPassword()) == null) {
            if (userRepository.findByUserId(user.getLoginId()) == null
                    && userRepository.findByEmail(user.getEmail()) == null) {
                if (userRepository.findByPhoneNumber(user.getPhoneNumber()) == null) {
                    String hashedPassword = UserService.encryptPassword(user.getPassword());
                    user.setPassword(hashedPassword);
                    User saveUser = UserDTOMapper.mapUser(user);
                    userRepository.save(saveUser);
                    responseMessage = "Account successfully registered.";
                } else {
                    responseMessage = "Phone number is already registered.";
                    throw new AdminAlreadyExistsException(responseMessage);
                }
            } else {
                responseMessage = "Username/email already exists.";
                throw new AdminAlreadyExistsException(responseMessage);
            }
        } else {
            if (!UserDTOChecker.validateUserDTO(user).isEmpty()) {
                responseMessage = UserDTOChecker.validateUserDTO(user).get(0);
                if (!responseMessage.isEmpty()) {
                    throw new InvalidInputException(responseMessage);
                }
            } else if (UserDTOChecker.validateUserDTOPassword(user.getPassword()) != null) {
                responseMessage = UserDTOChecker.validateUserDTOPassword(user.getPassword());
                if (!responseMessage.isEmpty()) {
                    throw new InvalidInputException(responseMessage);
                }
            }
        }

        return responseMessage;

    }

    @Override
    public Boolean authenticate(String login, String password) {
        String hashedPassword = UserService.encryptPassword(password);
        User accountById = userRepository.findByUserIdAndPassword(login, hashedPassword);
        User accountByEmail = userRepository.findByEmailAndPassword(login, hashedPassword);
        if (accountById != null || accountByEmail != null) {

           return true;
        }else{
            throw new LoginFailedException("Incorrect username/password.");
        }

    }

    @Override
    public String deleteUser(String id){
        String responseMessage = null;
        try {
            userRepository.deleteById(id);
            responseMessage = "Data successfully deleted.";
        } catch (EmptyResultDataAccessException e) {
            responseMessage = "No such account with username = " + id + ".";
        }
        return responseMessage;
    }

    @Override
    public String changePassword(String userId, String password1, String password2) {
        String responseMessage = null;
        String hashedPassword = UserService.encryptPassword(password1);
        User accountById = userRepository.findByUserIdAndPassword(userId, hashedPassword);
        User accountByEmail = userRepository.findByEmailAndPassword(userId, hashedPassword);
        String hashedPassword2 = UserService.encryptPassword(password2);

        if (UserDTOChecker.validateUserDTOPassword(password2) == null) {
            if (accountById != null) {
                accountById.setPassword(hashedPassword2);
                userRepository.save(accountById);
                responseMessage = "Password changed successfully.";
            } else if (accountByEmail != null) {
                accountByEmail.setPassword(hashedPassword2);
                userRepository.save(accountByEmail);
                responseMessage = "Password changed successfully.";
            } else {
                responseMessage = "Incorrect username/password.";
                throw new LoginFailedException(responseMessage);
            }
        } else {
            responseMessage = UserDTOChecker.validateUserDTOPassword(password2);
            if (responseMessage != null) {
                throw new InvalidInputException(responseMessage);
            }
        }
            return responseMessage;
    }
}
