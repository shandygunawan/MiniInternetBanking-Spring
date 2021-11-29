package com.minibank.controller;


import com.minibank.entity.PaymentPhone;
import com.minibank.entity.User;
import com.minibank.entity.UserAccount;
import com.minibank.service.PaymentService;
import com.minibank.service.UserService;
import com.minibank.util.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Tuple;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    UserService userService;

    @PostMapping("/telephone")
    public ModelAndView payTelephone(@ModelAttribute PaymentPhone paymentPhone, Model model) {
        User loggedInUser = getLoggedInUser();
        paymentPhone.setUser(loggedInUser);

        ValidationResult valResult = this.validatePaymentPhone(paymentPhone);

        if(!valResult.getResult()) {
            model.addAttribute("alert_type", "error");
            model.addAttribute("alert_message", valResult.getMessage());
        }
        else {
            paymentService.createPaymentPhone(paymentPhone);
            model.addAttribute("alert_type", "success");
            model.addAttribute("alert_message", "payment.telephone.success");
        }

        model.addAttribute("payment_telephone", new PaymentPhone());
        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());
        model.addAttribute("user_accounts", userAccounts);
        return new ModelAndView("payment_telephone");
    }

    private User getLoggedInUser() {
        // Get user id through auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }


    //
    // VALIDATION
    //
    private ValidationResult validatePaymentPhone(PaymentPhone paymentPhone) {

        UserAccount userAccount = userService.findUserAccountByAccountNumber(paymentPhone.getAccountNumber());

        if(userAccount == null) {
            return new ValidationResult(false, "error.userAccount.notFound");
        }

        // Check User Account's balance
        if(userAccount.getBalance() < paymentPhone.getAmount()) {
            return new ValidationResult(false, "error.userAccount.insufficientBalance");
        }

        return new ValidationResult(true, "");
    }
}
