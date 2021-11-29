package com.minibank.controller;

import com.minibank.entity.CreditBill;
import com.minibank.entity.CreditPayment;
import com.minibank.entity.User;
import com.minibank.entity.UserAccount;
import com.minibank.service.CreditBillService;
import com.minibank.service.CreditPaymentService;
import com.minibank.service.UserService;
import com.minibank.util.ValidationResult;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.StringMatcher;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.minibank.util.Utils.getCurrentMonthLocale;

@Controller
@RequestMapping("/creditCard")
public class CreditCardController {

    @Autowired
    CreditPaymentService creditPaymentService;

    @Autowired
    CreditBillService creditBillService;

    @Autowired
    UserService userService;


    @PostMapping("/payBill")
    public ModelAndView payBill(HttpServletRequest request, Model model) {
        String loggedInUsername = getLoggedInUsername();

        ValidationResult valResult = this.validatePayBill(request, loggedInUsername);

        String account_number = request.getParameter("account_number");
        CreditPayment cp = new CreditPayment();
        cp.setAmount(Integer.parseInt(request.getParameter("amount")));
        cp.setUser(getLoggedInUser());
        cp.setAccountNumber(account_number);
        cp.setDate(new java.sql.Date(new java.util.Date().getTime()));
        creditPaymentService.createCreditPayment(cp);

        String currentMonth = WordUtils.capitalizeFully(getCurrentMonthLocale());
        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());
        List<CreditBill> bills = creditBillService.getCreditBillsCurrentMonthDate(
                loggedInUsername,
                account_number
        );
        List<CreditPayment> payments = creditPaymentService.getCreditPaymentsCurrentMonthDate(
                loggedInUsername,
                account_number
        );

        // Get total bill amount
        Integer total_bill_amount = bills.stream().mapToInt(CreditBill::getAmount).sum();

        // Get total payment amount
        Integer total_paid = payments.stream().mapToInt(CreditPayment::getAmount).sum();

        Integer net_bill_amount = total_bill_amount - total_paid;
        if(net_bill_amount <= 0) net_bill_amount = 0;

        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), getLoggedInUser().getCreditDueDate());

        model.addAttribute("credit_bill", bills);
        model.addAttribute("credit_payment", payments);
        model.addAttribute("due_date", dueDate);
        model.addAttribute("current_month", currentMonth);
        model.addAttribute("net_bill_amount", net_bill_amount);
        model.addAttribute("alert_message", "credit.bill.success");
        model.addAttribute("alert_type", "success");
        model.addAttribute("user_accounts", userAccounts);

        return new ModelAndView("redirect:/creditCardBill");
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

    //
    // VALIDATION
    //
    private ValidationResult validatePayBill(HttpServletRequest request, String username) {
        Integer amount = Integer.parseInt(request.getParameter("amount"));
        String accountNumber = request.getParameter("account_number");

        UserAccount userAccount = userService.findUserAccountByAccountNumber(accountNumber);

        if(userAccount == null) {
            return new ValidationResult(false, "error.userAccount.notFound");
        }

        if(userAccount.getBalance() < amount) {
            return new ValidationResult(false, "error.userAccount.insufficientBalance");
        }

        return new ValidationResult(true, "");
    }
}
