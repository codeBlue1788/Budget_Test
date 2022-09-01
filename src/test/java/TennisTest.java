import com.tennis.model.Tennis;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisTest {

  private Tennis tennis;

  @BeforeEach
  void setUp() {
    tennis = new Tennis("Tom", "Jerry");
  }

  @Test
  void love_all() {
    String score = tennis.score();
    Assertions.assertEquals("love all", score);
  }

  @Test
  void fifteen_all() {
    addFirstPlayerScore(1);
    addSecondPlayerScore(1);
    String score = tennis.score();
    Assertions.assertEquals("fifteen all", score);
  }

  @Test
  void deuce() {
    addFirstPlayerScore(3);
    addSecondPlayerScore(3);
    String score = tennis.score();
    Assertions.assertEquals("deuce", score);
  }

  @Test
  void fifteen_love() {
    addFirstPlayerScore(1);
    String score = tennis.score();
    Assertions.assertEquals("fifteen love", score);
  }

  @Test
  void Tom_adv() {
    addFirstPlayerScore(4);
    addSecondPlayerScore(3);
    String score = tennis.score();
    Assertions.assertEquals("Tom adv", score);
  }

  @Test
  void Tom_win() {
    addFirstPlayerScore(5);
    addSecondPlayerScore(3);
    String score = tennis.score();
    Assertions.assertEquals("Tom win", score);
  }

  @Test
  void Tom_win_4_1() {
    addFirstPlayerScore(4);
    addSecondPlayerScore(1);
    String score = tennis.score();
    Assertions.assertEquals("Tom win", score);
  }

  private void addFirstPlayerScore(int score) {
    for (int i = 0; i < score; i++) {
      tennis.firstPlayerScore();
    }
  }

  private void addSecondPlayerScore(int score) {
    for (int i = 0; i < score; i++) {
      tennis.secondPlayerScore();
    }
  }
}
