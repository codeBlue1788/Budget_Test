package com.budget.dao.entity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {

  private String yearMonth;

  private int amount;

  public Budget(String yearMonth, int amount) {
    this.yearMonth = yearMonth;
    this.amount = amount;
  }

  public String getYearMonth() {
    return yearMonth;
  }

  public int getAmount() {
    return amount;
  }

  public YearMonth getYearMonthObj() {
    return YearMonth.parse(getYearMonth(),
        DateTimeFormatter.ofPattern("yyyyMM"));
  }

  public int getDailyAmount() {
    return getAmount() / getYearMonthObj().lengthOfMonth();
  }

  public LocalDate getLastDayOfMonth() {
    return getYearMonthObj()
        .atEndOfMonth();
  }

  public LocalDate getFirstDayOfMonth() {
    return getYearMonthObj().atDay(1);
  }
}
