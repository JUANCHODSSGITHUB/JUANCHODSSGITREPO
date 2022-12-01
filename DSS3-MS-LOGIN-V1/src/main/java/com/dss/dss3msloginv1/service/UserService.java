package com.dss.dss3msloginv1.service;

import com.dss.dss3msloginv1.dto.UserDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface UserService {

    String addUser(UserDTO user);

    String authenticate(String userName, String password);

    String deleteUser(String id);

    String changePassword(String id, String password1, String password2);

    static String encryptPassword(String password, String algo){
        MessageDigest messageDigest;
        String hashedPassword = null;
        try {
            messageDigest = MessageDigest.getInstance(algo);
            byte[] passwordInBytes = password.getBytes();
            messageDigest.update(passwordInBytes);
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for(byte byteVar : resultByteArray) {
                String formattedString = String.format("%02x", byteVar);
                stringBuilder.append(formattedString);
            }
            hashedPassword = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new NullPointerException(e.getMessage());
        }
        return hashedPassword;
    }

}
