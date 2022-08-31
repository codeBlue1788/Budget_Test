package com.budget.service.model;

import com.budget.dao.entity.Budget;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Period {

  private final LocalDate startLocalDate;
  private final LocalDate endLocalDate;

  public Period(LocalDate startLocalDate, LocalDate endLocalDate) {
    this.startLocalDate = startLocalDate;
    this.endLocalDate = endLocalDate;
  }

  public LocalDate getStartLocalDate() {
    return startLocalDate;
  }

  public LocalDate getEndLocalDate() {
    return endLocalDate;
  }

  public boolean isInValid() {
    return getStartLocalDate().isAfter(getEndLocalDate());
  }

  public int getOverLappingDays(Budget budget) {
    // 判斷第一天與最後一天的區間
    LocalDate firstDay = budget.getFirstDayOfMonth().isBefore(
        this.getStartLocalDate()) ? this.getStartLocalDate()
        : budget.getFirstDayOfMonth();
    LocalDate endDay =
        budget.getLastDayOfMonth().isBefore(this.getEndLocalDate()) ? budget.getLastDayOfMonth()
            : this.getEndLocalDate();

    return firstDay.isBefore(endDay) || firstDay.equals(endDay) ?
        (int) ChronoUnit.DAYS.between(firstDay, endDay) + 1 : 0;
  }
}
