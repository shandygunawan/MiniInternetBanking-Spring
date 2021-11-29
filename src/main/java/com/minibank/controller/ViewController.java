package com.minibank.controller;

import com.minibank.entity.*;
import com.minibank.service.CreditBillService;
import com.minibank.service.CreditPaymentService;
import com.minibank.service.UserService;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

import static com.minibank.util.Utils.getCurrentMonthLocale;

@Controller
public class ViewController {

    @Autowired
    private CreditBillService creditBillService;

    @Autowired
    private CreditPaymentService creditPaymentService;

    @Autowired
    private UserService userService;

    private Gson gson;

    public ViewController() {
        gson = new Gson();
    }

    @RequestMapping(value = "/login")
    public ModelAndView loginPage(Model model) {
        model.addAttribute("user", new User());
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/")
    public ModelAndView indexpage(Model model) {
        model.addAttribute("alert_message", "No Error. All is well");
        model.addAttribute("alert_type", "success");
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/purchaseVoucher")
    public ModelAndView purchaseVoucherPage(Model model) {

        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());
        model.addAttribute("user_accounts", userAccounts);

        List<SavedPhone> savedPhones = userService.getSavedPhoneByUsername(getLoggedInUser());
        model.addAttribute("saved_phone_numbers", gson.toJson(savedPhones));

        return new ModelAndView("purchase_voucher");
    }

    @RequestMapping(value = "/paymentTelephone")
    public ModelAndView paymentTelephonePage(Model model) {

        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());
        model.addAttribute("user_accounts", userAccounts);

        List<SavedPhone> savedPhones = userService.getSavedPhoneByUsername(getLoggedInUser());
        model.addAttribute("saved_phone_numbers", gson.toJson(savedPhones));

        model.addAttribute("payment_telephone", new PaymentPhone());
        return new ModelAndView("payment_telephone");
    }

    @RequestMapping("/creditCardTransaction")
    public ModelAndView creditCardTransactionPage(HttpServletRequest request, Model model) {
        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());

        String accountNumber = request.getParameter("accountNumber");

        if(accountNumber == null || accountNumber.equals("")) {
            accountNumber = userAccounts.get(0).getAccountNumber();
        }

        List<CreditBill> bills = creditBillService.getCreditBillsBeforeCurrentMonthDate(
                getLoggedInUsername(),
                accountNumber
        );

        model.addAttribute("user_accounts", userAccounts);
        model.addAttribute("credit_transaction", bills);
        return new ModelAndView("credit_transaction");
    }

    @RequestMapping(value = "/creditCardBill")
    public ModelAndView creditCardBillsPage(HttpServletRequest request, Model model) {
        String currentMonth = WordUtils.capitalizeFully(getCurrentMonthLocale());
        List<UserAccount> userAccounts = userService.findUserAccountsByUser(getLoggedInUser());
        String loggedInUsername = getLoggedInUsername();

        String accountNumber = request.getParameter("accountNumber");

        if(accountNumber == null || accountNumber.equals("")) {
            accountNumber = userAccounts.get(0).getAccountNumber();
        }

        List<CreditBill> bills = creditBillService.getCreditBillsCurrentMonthDate(
                loggedInUsername,
                accountNumber
        );
        List<CreditPayment> payments = creditPaymentService.getCreditPaymentsCurrentMonthDate(
                loggedInUsername,
                accountNumber
        );

        // Get total bill amount
        Integer total_bill_amount = bills.stream().mapToInt(CreditBill::getAmount).sum();

        // Get total payment amount
        Integer total_paid = payments.stream().mapToInt(CreditPayment::getAmount).sum();

        // Get net amount
        Integer net_bill_amount = total_bill_amount - total_paid;
        if (net_bill_amount <= 0) net_bill_amount = 0;

        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), getLoggedInUser().getCreditDueDate());

        model.addAttribute("account_number", accountNumber);
        model.addAttribute("credit_bill", bills);
        model.addAttribute("credit_payment", payments);
        model.addAttribute("due_date", dueDate);
        model.addAttribute("current_month", currentMonth);
        model.addAttribute("net_bill_amount", net_bill_amount);
        model.addAttribute("user_accounts", userAccounts);

        return new ModelAndView("credit_bill");
    }

    @RequestMapping(value = "/adminPassword")
    public ModelAndView adminChangePasswordPage(Model model) {
        return new ModelAndView("admin_password");
    }

    @RequestMapping(value = "/adminLanguage")
    public ModelAndView adminChangeLanguagePage(Model model) {
        return new ModelAndView("admin_language");
    }

    @RequestMapping(value= "/savedPhone")
    public ModelAndView savedPhoneNumberPage(Model model) {
        List<SavedPhone> savedPhones = userService.getSavedPhoneByUsername(getLoggedInUser());
        model.addAttribute("saved_phone_numbers", savedPhones);
        model.addAttribute("savedPhone", new SavedPhone());
        return new ModelAndView("saved_phone");
    }

    @RequestMapping(value="/userAccount")
    public ModelAndView userAccountPage(Model model) {
        List<UserAccount> accounts = userService.findUserAccountsByUser(getLoggedInUser());
        model.addAttribute("user_accounts", accounts);
        model.addAttribute("userAccount", new UserAccount());
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
