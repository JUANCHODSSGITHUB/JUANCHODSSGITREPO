package com.dss.dss3msloginv1.service;

import com.dss.dss3msloginv1.dto.UserDTO;
import com.dss.dss3msloginv1.dto.util.UserDTOChecker;
import com.dss.dss3msloginv1.dto.util.UserDTOMapper;
import com.dss.dss3msloginv1.entity.User;
import com.dss.dss3msloginv1.repository.UserRepository;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG =   LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(UserDTO user) {
        String responseMessage = null;


        UserDTOChecker userDTOChecker = new UserDTOChecker();
        UserDTOMapper userDTOMapper = new UserDTOMapper();
        if(userDTOChecker.validateUserDTO(user).size() == 0 && userDTOChecker.validateUserDTOPassword(user.getPassword()) == null) {

            if (userRepository.findByUserId(user.getLoginId()) == null
                    && userRepository.findByEmail(user.getEmail()) == null) {
                if(userRepository.findByPhoneNumber(user.getPhoneNumber()) == null) {
                    String hashedPassword = UserService.encryptPassword(user.getPassword());
                    user.setPassword(hashedPassword);
                    User saveUser = UserDTOMapper.mapUser(user);
                    userRepository.save(saveUser);
                    responseMessage = "Account successfully registered.";
                }else{
                    responseMessage = "Phone number is already registered.";
                }
            } else {
                responseMessage = "Username/email already exists.";
            }
        }else{
            if(userDTOChecker.validateUserDTO(user).size() > 0){
                responseMessage = userDTOChecker.validateUserDTO(user).get(0);
            }else if(userDTOChecker.validateUserDTOPassword(user.getPassword()) != null){
                responseMessage = userDTOChecker.validateUserDTOPassword(user.getPassword());
            }
        }

        return  responseMessage;

    }

    @Override
    public Boolean authenticate(String Login, String password) {
        String hashedPassword = UserService.encryptPassword(password);
        User accountById = userRepository.findByUserIdAndPassword(Login,hashedPassword);
        User accountByEmail = userRepository.findByEmailAndPassword(Login,hashedPassword);
        if(accountById != null || accountByEmail != null) {
            return true;
        }
        return false;
    }

    @Override
    public String deleteUser(String id) {
        String responseMessage = null;
        try {
            userRepository.deleteById(id);
            responseMessage = "Data successfully deleted.";
        }catch(EmptyResultDataAccessException e){
            responseMessage = "No such account with username = " + id + ".";
            LOG.error(e.getMessage());
        }
        return responseMessage;
    }

    @Override
    public String changePassword(String userId, String password1, String password2) {
        String responseMessage = null;
        UserDTOChecker userDTOChecker = new UserDTOChecker();
        boolean authenticated = false;
        String hashedPassword = UserService.encryptPassword(password1);
        User accountById = userRepository.findByUserIdAndPassword(userId,hashedPassword);
        User accountByEmail = userRepository.findByEmailAndPassword(userId,hashedPassword);
        String hashedPassword2 = UserService.encryptPassword(password2);

        if(accountById != null){
            if(userDTOChecker.validateUserDTOPassword(password2) == null) {
                accountById.setPassword(hashedPassword2);
                 userRepository.save(accountById);
                responseMessage = "Password changed successfully.";
            }else{
                responseMessage = userDTOChecker.validateUserDTOPassword(password2);
            }
        }else if(accountByEmail != null){
            if(userDTOChecker.validateUserDTOPassword(password2) == null) {
                accountByEmail.setPassword(hashedPassword2);
                userRepository.save(accountByEmail);
                responseMessage = "Password changed successfully.";
            }else{
                responseMessage = userDTOChecker.validateUserDTOPassword(password2);
            }
        }else{
            responseMessage = "Incorrect username/password.";
        }

        return responseMessage;
    }
}
