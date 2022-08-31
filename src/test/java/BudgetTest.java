import com.budget.dao.IBudgetRepo;
import com.budget.dao.entity.Budget;
import com.budget.service.BudgetService;
import com.budget.service.model.Period;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BudgetTest {

  private BudgetService budgetService;

  private IBudgetRepo budgetRepo;

  @BeforeEach
  void setUp() {
    budgetRepo = Mockito.mock(IBudgetRepo.class);
    budgetService = new BudgetService(budgetRepo);
  }

  @DisplayName("日期格式錯誤")
  @Test
  void test1() {
    // Arrange
    // Act
    // Assert
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      budgetService.queryBudget(new Period(LocalDate.of(2022, 7, 25), LocalDate.of(2022, 7, 5)));
    }, "日期格式錯誤");
  }

  @DisplayName("日期格式錯誤")
  @Test
  void test3() {
    // Arrange
    // Act
    // Assert
    Assertions.assertThrows(DateTimeException.class, () -> {
      budgetService.queryBudget(new Period(LocalDate.of(2022, 6, 5), LocalDate.of(2022, 6, 31)));
    }, "日期格式錯誤");
  }

  @DisplayName("資料庫無該月資料")
  @Test
  void test2() {
    // Arrange
    givenBudget();
    // Act
    int amount = budgetService.queryBudget(
        new Period(LocalDate.of(2022, 9, 5), LocalDate.of(2022, 9, 15)));
    // Assert
    Assertions.assertEquals(0, amount);
  }

  @DisplayName("資料庫有不足月的預算")
  @Test
  void test4() {
    // Arrange
    givenBudget();
    // Act
    int amount = budgetService.queryBudget(
        new Period(LocalDate.of(2022, 7, 5), LocalDate.of(2022, 7, 6)));
    // Assert
    Assertions.assertEquals(200, amount);
  }

  @DisplayName("資料庫有足月的預算")
  @Test
  void test5() {
    // Arrange
    givenBudget();
    // Act
    int amount = budgetService.queryBudget(
        new Period(LocalDate.of(2022, 7, 1), LocalDate.of(2022, 7, 31)));
    // Assert
    Assertions.assertEquals(3100, amount);
  }

  @DisplayName("跨月情境")
  @Test
  void test6() {
    // Arrange
    givenBudget();
    // Act
    int amount = budgetService.queryBudget(
        new Period(LocalDate.of(2022, 6, 15), LocalDate.of(2022, 8, 10)));
    // Assert
    Assertions.assertEquals(6700, amount);
  }

  @DisplayName("跨月情境_中間月份沒資料")
  @Test
  void test7() {
    // Arrange
    givenBudget2();
    // Act
    int amount = budgetService.queryBudget(
        new Period(LocalDate.of(2022, 6, 15), LocalDate.of(2022, 8, 10)));
    // Assert
    Assertions.assertEquals(3600, amount);
  }

  @DisplayName("跨月情境_臨界")
  @Test
  void test8() {
    // Arrange
    givenBudget();
    // Act
    int amount = budgetService.queryBudget(
        new Period(LocalDate.of(2022, 7, 31), LocalDate.of(2022, 8, 1)));
    // Assert
    Assertions.assertEquals(300, amount);
  }

  private void givenBudget() {
    Mockito.when(budgetRepo.findAll()).thenReturn(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202207", 3100),
        new Budget("202208", 6200)
    ));
  }

  private void givenBudget2() {
    Mockito.when(budgetRepo.findAll()).thenReturn(Arrays.asList(
        new Budget("202206", 3000),
        new Budget("202208", 6200)
    ));
  }
}
