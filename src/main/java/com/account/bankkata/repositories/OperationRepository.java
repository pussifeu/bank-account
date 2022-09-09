package com.account.bankkata.repositories;

import com.account.bankkata.helpers.ClockHelper;
import com.account.bankkata.models.Operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperationRepository {
    private final ClockHelper clockHelper;
    private final List<Operation> operations = new ArrayList<>();

    public OperationRepository(ClockHelper clockHelper) {
        this.clockHelper = clockHelper;
    }

    public void addDeposit(int amount) {
        Operation deposit = new Operation(clockHelper.todayAsString(), amount);
        operations.add(deposit);
    }

    public void addWithdrawal(int amount) {
        Operation withdrawal = new Operation(clockHelper.todayAsString(), -amount);
        operations.add(withdrawal);
    }

    public List<Operation> allOperations() {
        return Collections.unmodifiableList(operations);
    }
}
