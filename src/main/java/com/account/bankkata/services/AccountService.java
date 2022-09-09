package com.account.bankkata.services;

import com.account.bankkata.printer.StatementPrinter;
import com.account.bankkata.repositories.OperationRepository;

public class AccountService {

    private final OperationRepository operationRepository;
    private final StatementPrinter statementPrinter;
    public AccountService(OperationRepository operationRepository, StatementPrinter statementPrinter) {
        this.operationRepository = operationRepository;
        this.statementPrinter = statementPrinter;
    }
    public void deposit(int amount) {
        operationRepository.addDeposit(amount);
    }
    public void withdrawal(int amount) {
        operationRepository.addWithdrawal(amount);
    }
    public void printStatement() {
        statementPrinter.print(operationRepository.allOperations());
    }
}
