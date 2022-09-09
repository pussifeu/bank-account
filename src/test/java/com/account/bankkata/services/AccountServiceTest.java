package com.account.bankkata.services;

import com.account.bankkata.models.Operation;
import com.account.bankkata.printer.StatementPrinter;
import com.account.bankkata.repositories.OperationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock OperationRepository operationRepository;
    @Mock StatementPrinter statementPrinter;
    private AccountService accountService;

    @Before
    public void setUp() {
        accountService =  new AccountService(operationRepository, statementPrinter);
    }

    @Test
    public void saveDeposit() {
        accountService.deposit(100);
        verify(operationRepository).addDeposit(100);
    }

    @Test
    public void saveWithdraw() {
        accountService.withdrawal(100);
        verify(operationRepository).addWithdrawal(100);
    }

    @Test
    public void printStatement() {
        List<Operation> operations = List.of(new Operation("08/09/2022", 100));
        given(operationRepository.allOperations()).willReturn(operations);
        accountService.printStatement();
        verify(statementPrinter).print(operations);
    }
}