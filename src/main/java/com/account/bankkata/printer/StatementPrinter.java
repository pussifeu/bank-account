package com.account.bankkata.printer;

import com.account.bankkata.models.Operation;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toCollection;

public class StatementPrinter {
    public static final String HEADER = "date | amount | balance";
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");
    private final ConsolePrinter consolePrinter;

    public StatementPrinter(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void print(List<Operation> operations) {
        consolePrinter.printLine(HEADER);
        printLines(operations);
    }

    private void printLines(List<Operation> operations) {
        AtomicInteger balance = new AtomicInteger(0);
        operations.stream()
                .map(operation -> statementLine(operation, balance))
                .collect(toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(consolePrinter::printLine);
    }

    private String statementLine(Operation operation, AtomicInteger balance) {
        return operation.getDate()+ " | "+decimalFormat.format(operation.getAmount())+" | "+decimalFormat.format(balance.addAndGet(operation.getAmount()));
    }

}
