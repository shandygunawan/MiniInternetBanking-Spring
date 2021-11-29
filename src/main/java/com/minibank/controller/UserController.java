package com.minibank.controller;

import com.minibank.entity.SavedPhone;
import com.minibank.entity.User;
import com.minibank.entity.UserAccount;
import com.minibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/updatePassword")
    public ModelAndView updatePassword(HttpServletRequest request, Model model) {
        String new_password = request.getParameter("new_password");
        String old_password = request.getParameter("old_password");
        String confirm_password = request.getParameter("confirm_password");

        // Check if new password and confirm password is equal
        if (new_password.equals(confirm_password)) {
            // Update password, password encryption is handled in userService
            userService.updatePassword(old_password, new_password, getLoggedInUsername());

            // If success, returns to login page
            model.addAttribute("user", new User());
            return new ModelAndView("login");
        } else {
            return new ModelAndView("index");
        }
    }

    @PostMapping("/savePhoneNumber")
    public ModelAndView savePhoneNumber(@ModelAttribute SavedPhone savedPhone, Model model) {
        savedPhone.setUser(getLoggedInUser());
        userService.createSavedPhone(savedPhone);

        List<SavedPhone> savedPhones = userService.getSavedPhoneByUsername(getLoggedInUser());
        model.addAttribute("saved_phone_numbers", savedPhones);
        model.addAttribute("savedPhone", new SavedPhone());
        model.addAttribute("alert_type", "success");
        model.addAttribute("alert_message", "profile.savedPhone.success");
        return new ModelAndView("saved_phone");
    }

    @PostMapping("/createUserAccount")
    public ModelAndView createUserAccount(@ModelAttribute UserAccount userAccount, Model model) {
        userAccount.setUser(getLoggedInUser());

        userService.createUserAccount(userAccount);

        List<UserAccount> accounts = userService.findUserAccountsByUser(getLoggedInUser());
        model.addAttribute("user_accounts", accounts);
        model.addAttribute("userAccount", new UserAccount());
        model.addAttribute("alert_type", "success");
        model.addAttribute("alert_message", "profile.userAccount.success");
        return new ModelAndView("user_account");
    }

    private User getLoggedInUser() {
        // Get user id through auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }

    private String getLoggedInUsername() {
        // Get user id through auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
