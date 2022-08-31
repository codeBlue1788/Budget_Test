package com.budget.dao;

import com.budget.dao.entity.Budget;
import java.util.List;

public interface IBudgetRepo {

  List<Budget> findAll();

}
