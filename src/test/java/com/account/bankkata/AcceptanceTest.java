package com.account.bankkata;

import com.account.bankkata.helpers.ClockHelper;
import com.account.bankkata.printer.ConsolePrinter;
import com.account.bankkata.printer.StatementPrinter;
import com.account.bankkata.repositories.OperationRepository;
import com.account.bankkata.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;


@RunWith(MockitoJUnitRunner.class)
public class AcceptanceTest {
    @Mock
    ConsolePrinter consolePrinter;
    @Mock
    ClockHelper clockHelper;
    private AccountService accountService;

    @Before
    public void setUp() {
        OperationRepository operationRepository = new OperationRepository(clockHelper);
        StatementPrinter statementPrinter = new StatementPrinter(consolePrinter);
        accountService =  new AccountService(operationRepository, statementPrinter);
    }

    @Test
    public void printAllOperation() {
        given(clockHelper.todayAsString()).willReturn("07/09/2022", "08/09/2022", "09/09/2022");
        accountService.deposit(1000);
        accountService.withdrawal(100);
        accountService.deposit(500);
        accountService.printStatement();
        InOrder inOrder = inOrder(consolePrinter);
        inOrder.verify(consolePrinter).printLine("date | amount | balance");
        inOrder.verify(consolePrinter).printLine("09/09/2022 | 500,00 | 1400,00");
        inOrder.verify(consolePrinter).printLine("08/09/2022 | -100,00 | 900,00");
        inOrder.verify(consolePrinter).printLine("07/09/2022 | 1000,00 | 1000,00");
    }
}
