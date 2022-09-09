package com.account.bankkata.helpers;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ClockHelperTest {

    @Before
    public void setUp() {
    }

    @Test
    public void checkDateFormat() {
        ClockHelper clockHelper = new TestableClockHelper();
        String date = clockHelper.todayAsString();
        assertThat(date, is("08/09/2022"));
    }

    private static class TestableClockHelper extends ClockHelper {
        @Override
        protected LocalDate today() {
            return LocalDate.of(2022, 9, 8);
        }
    }
}