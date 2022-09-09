package com.account.bankkata.models;

import java.util.Objects;

public class Operation {
    private final String date;
    private final int amount;

    public Operation(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        if (amount != operation.amount) return false;
        return Objects.equals(date, operation.date);
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + amount;
        return result;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
