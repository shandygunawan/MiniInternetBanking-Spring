package com.minibank.service;

import com.minibank.entity.SavedPhone;
import com.minibank.entity.User;
import com.minibank.entity.UserAccount;
import com.minibank.repository.SavedPhoneRepository;
import com.minibank.repository.UserAccountRepository;
import com.minibank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SavedPhoneRepository savedPhoneRepo;

    @Autowired
    private UserAccountRepository accountRepo;
    

    public void save(User user) {
        userRepo.save(user);
    }

    public void delete(String username) {
        userRepo.deleteById(username);
    }

    public List<User> findAllOrderById() {
        return userRepo.findAll();
    }

    public void updatePassword(String old_password, String new_password, String username) {
        User user = userRepo.findUserByUsername(username);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Check old password input and existing password
        if(passwordEncoder.matches(old_password, user.getPassword())) {

            // If equal, encode new password
            userRepo.updatePasswordByUsername(passwordEncoder.encode(new_password), username);
        }
    }

    public User findByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }


    //
    // Saved Phone Services
    //

    public List<SavedPhone> getSavedPhoneByUsername(User user) {
        return savedPhoneRepo.findSavedPhonesByUser(user);
    }

    public void createSavedPhone(SavedPhone savedPhone) {
        savedPhoneRepo.save(savedPhone);
    }


    //
    // User Account Services
    //
    public void createUserAccount(UserAccount userAccount) {
        accountRepo.save(userAccount);
    }

    public List<UserAccount> findUserAccountsByUser(User user) {
        return accountRepo.findUserAccountsByUserOrderByAccountNumber(user);
    }

    public UserAccount findUserAccountByAccountNumber(String accountNumber) {
        return accountRepo.findUserAccountByAccountNumber(accountNumber);
    }
}
