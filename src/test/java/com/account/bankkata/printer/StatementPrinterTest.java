package com.account.bankkata.printer;


import com.account.bankkata.models.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterTest {
    public static final List<Operation> OPERATIONS = EMPTY_LIST;
    @Mock ConsolePrinter consolePrinter;

    private StatementPrinter statementPrinter;

    @Before
    public void setUp() {
        statementPrinter = new StatementPrinter(consolePrinter);
    }

    @Test
    public void printHeader() {
        statementPrinter.print(OPERATIONS);
        verify(consolePrinter).printLine("date | amount | balance");
    }

    @Test
    public void printOperations() {
        List<Operation> operations = operationsContains(
                deposit("07/09/2022", 1000),
                withdrawal("08/09/2022", 100),
                deposit("09/09/2022", 500)
        );
        statementPrinter.print(operations);
        InOrder inOrder = inOrder(consolePrinter);
        inOrder.verify(consolePrinter).printLine("date | amount | balance");
        inOrder.verify(consolePrinter).printLine("09/09/2022 | 500,00 | 1400,00");
        inOrder.verify(consolePrinter).printLine("08/09/2022 | -100,00 | 900,00");
        inOrder.verify(consolePrinter).printLine("07/09/2022 | 1000,00 | 1000,00");
    }

    private List<Operation> operationsContains(Operation... operations) {
        return asList(operations);
    }
    private Operation deposit(String date, int amount) {
        return new Operation(date, amount);
    }
    private Operation withdrawal(String date, int amount) {
        return new Operation(date, -amount);
    }

}