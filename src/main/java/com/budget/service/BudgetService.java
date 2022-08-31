package com.budget.service;

import com.budget.dao.IBudgetRepo;
import com.budget.dao.entity.Budget;
import com.budget.service.model.Period;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BudgetService {

  private IBudgetRepo budgetRepo;

  public BudgetService(IBudgetRepo budgetRepo) {
    this.budgetRepo = budgetRepo;
  }

  public int queryBudget(Period period) {
    // 若時間格式錯誤
    if (period.isInValid()) {
      throw new IllegalArgumentException("日期格式錯誤");
    }

    // 從資料庫獲取資料
    List<Budget> budgets = budgetRepo.findAll();

    return budgets
        .stream()
        .mapToInt(budget -> budget.getDailyAmount() * period.getOverLappingDays(budget))
        .sum();
  }

}
