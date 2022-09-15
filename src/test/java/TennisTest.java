import com.tennis.model.Tennis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TennisTest {

  @Test
  void fifteen_all() {
    Tennis tennis = new Tennis("Tom", "Jerry");
    tennis.firstPlayerScore();
    tennis.secondPlayerScore();

    Assertions.assertEquals("fifteen all", tennis.score());
  }
}
