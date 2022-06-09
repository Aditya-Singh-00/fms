package com.mvc.fraudmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.mvc.fraudmanagement.entities.Transaction;
import com.mvc.fraudmanagement.services.TransactionService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

@Controller
@SessionAttributes({ "transaction", "user" })
public class TransactionFraudsController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/transaction-fraud", method = RequestMethod.GET)
    public String showTransaction() {
        return "fraud-pages/transaction-fraud";
    }

    @RequestMapping(value = "/transaction-registration", method = RequestMethod.GET)
    public String showTransactionRegistration(ModelMap model) {
        model.addAttribute("transaction", new Transaction());
        return "registration-forms/transaction-registration";
    }

    @RequestMapping(value = "/transaction-registration", method = RequestMethod.POST)
    public String saveTransactionRegistration(ModelMap model, @Valid Transaction transaction, BindingResult result) {

        if (result.hasErrors()) {
            return "registration-forms/transaction-registration";
        }

        transactionService.save(transaction);

        return "redirect:/transaction-fraud";
    }

    @RequestMapping(value = "/transaction-show", method = RequestMethod.GET)
    public String viewAllTransaction(ModelMap model, @RequestParam String id) {
        Transaction transaction = transactionService.getTransactionByUserId(id);
        if (transaction instanceof Transaction) {
            model.put("transaction", transaction);
        } else {
            model.put("errorMessage", "Transaction does not exist");
        }
        return "dashboards/transaction-dashboard";
    }

}