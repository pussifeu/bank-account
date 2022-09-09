package com.account.bankkata;

import com.account.bankkata.helpers.ClockHelper;
import com.account.bankkata.printer.ConsolePrinter;
import com.account.bankkata.printer.StatementPrinter;
import com.account.bankkata.repositories.OperationRepository;
import com.account.bankkata.services.AccountService;

public class BankKataApplication {
    public static void main(String[] args) {
        ClockHelper clockHelper = new ClockHelper();
        OperationRepository operationRepository = new OperationRepository(clockHelper);
        ConsolePrinter consolePrinter = new ConsolePrinter();
        StatementPrinter statementPrinter = new StatementPrinter(consolePrinter);
        AccountService accountService = new AccountService(operationRepository, statementPrinter);

        accountService.deposit(1000);
        accountService.withdrawal(400);
        accountService.deposit(100);

        accountService.printStatement();
    }
}
