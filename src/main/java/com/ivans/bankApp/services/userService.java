package com.ivans.bankApp.services;
import com.ivans.bankApp.entity.userDetails;
import com.ivans.bankApp.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

@Service
public class userService {
    private final usersRepository usersRepository;


    @Autowired
    public userService(usersRepository userRepository) {
        this.usersRepository = userRepository;
    }

    public userDetails getUser(String username, String password){

        List<userDetails> temp = usersRepository.findAll();
        for(userDetails iterator : temp){
            if(iterator.getUsername() == username
                    && iterator.getPassword() == hashString(password)){
                return iterator;
            }
        }
        return null;
    }

    public userDetails createUser(String username, String plainPassword) {
        userDetails user = new userDetails();
        user.setUsername(username);
        // Hash the password
        String hashedPassword = hashString(plainPassword);
        user.setPassword(hashedPassword);

        return usersRepository.save(user);
    }

    public static String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());

            Formatter formatter = new Formatter();
            for (byte b : hashBytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
