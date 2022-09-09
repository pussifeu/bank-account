package com.account.bankkata.repositories;


import com.account.bankkata.helpers.ClockHelper;
import com.account.bankkata.models.Operation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OperationRepositoryTest {
    @Mock
    ClockHelper clockHelper;
    private OperationRepository operationRepository;
    private static final String DAY = "08/09/2022";

    @Before
    public void setUp() {
        operationRepository = new OperationRepository(clockHelper);
        given(clockHelper.todayAsString()).willReturn(DAY);
    }

    @Test
    public void createAndStoreDepositOperation() {
        operationRepository.addDeposit(100);
        List<Operation> operations = operationRepository.allOperations();
        assertThat(operations.size(), is(1));
        assertThat(operations.get(0), is(operation(100)));
    }

    @Test
    public void createAndStoreWithdrawalOperation() {
        operationRepository.addWithdrawal(100);
        List<Operation> operations = operationRepository.allOperations();
        assertThat(operations.size(), is(1));
        assertThat(operations.get(0), is(operation(-100)));
    }
    private Operation operation(int amount) {
        return new Operation(OperationRepositoryTest.DAY, amount);
    }
}