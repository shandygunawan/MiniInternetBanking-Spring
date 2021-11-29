package com.minibank.controller;

import com.minibank.entity.PurchaseVoucher;
import com.minibank.entity.User;
import com.minibank.entity.UserAccount;
import com.minibank.service.PurchaseService;
import com.minibank.service.UserService;
import com.minibank.util.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    PurchaseService voucherService;

    @Autowired
    UserService userService;


    @RequestMapping("/voucher")
    public ModelAndView purchaseVoucher(HttpServletRequest request, Model model) {
        String provider = request.getParameter("provider");
        String phoneNumber = request.getParameter("phone_number");
        String token = request.getParameter("token");

        ValidationResult valResult = this.validatePurchaseVoucher(request);

        if(!valResult.getResult()) {
            model.addAttribute("alert_type", "error");
            model.addAttribute("alert_message", valResult.getMessage());
        } else {
            PurchaseVoucher purchaseVoucher = new PurchaseVoucher();
            purchaseVoucher.setProvider(provider);
            purchaseVoucher.setPhoneNumber(phoneNumber);
            purchaseVoucher.setToken(token);
            purchaseVoucher.setUser(getLoggedInUser());

            voucherService.createPurchaseVoucher(purchaseVoucher);
            model.addAttribute("alert_type", "success");
            model.addAttribute("alert_message", "purchase.voucher.success");
        }

        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());
        model.addAttribute("user_accounts", userAccounts);
        return new ModelAndView("purchase_voucher");
    }

    private User getLoggedInUser() {
        // Get user id through auth
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }

    //
    // VALIDATION
    //
    private ValidationResult validatePurchaseVoucher(HttpServletRequest request) {
        String apply2 = request.getParameter("apply2");
        String accountNumber = request.getParameter("account_number");

        if(apply2 == null || apply2.equals("")) {
            return new ValidationResult(false, "purchase.voucher.failed.apply2Empty");
        }

        UserAccount userAccount = userService.findUserAccountByAccountNumber(accountNumber);

        if(userAccount == null) {
            return new ValidationResult(false, "error.userAccount.notFound");
        }

        if(userAccount.getBalance() < PurchaseVoucher.AMOUNT) {
            return new ValidationResult(false, "error.userAccount.insufficientBalance");
        }

        return new ValidationResult(true, "");
    }

}
