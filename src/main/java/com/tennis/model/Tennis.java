package com.tennis.model;

import java.util.HashMap;
import java.util.Map;

public class Tennis {

  private String firstPlayerName;

  private String secondPlayerName;

  public Tennis(String firstPlayerName, String secondPlayerName) {
    this.firstPlayerName = firstPlayerName;
    this.secondPlayerName = secondPlayerName;
  }

  private int firstPlayerScore;

  private int secondPlayerScore;

  public void firstPlayerScore() {
    this.firstPlayerScore++;
  }

  public void secondPlayerScore() {
    this.secondPlayerScore++;
  }

  public String score() {
    Map<Integer, String> lookUp = new HashMap<>();
    lookUp.put(0, "love");
    lookUp.put(1, "fifteen");
    lookUp.put(2, "thirty");
    lookUp.put(3, "forty");

    if (isEven()) {
      if (isBothGamePoint()) {
        return "deuce";
      }
      return lookUp.get(this.firstPlayerScore) + " all";
    }

    String advName = this.firstPlayerScore > this.secondPlayerScore ? this.firstPlayerName
        : this.secondPlayerName;

    if (isBothGamePoint()) {
      if (isDiffSmallerThan2()) {
        return advName + " " + "adv";
      }
      return advName + " " + "win";
    }

    if (isOneOverGamePoint()) {
      return advName + " " + "win";
    }

    return lookUp.get(this.firstPlayerScore) + " " + lookUp.get(this.secondPlayerScore);

  }

  private boolean isDiffSmallerThan2() {
    return Math.abs(this.firstPlayerScore - this.secondPlayerScore) < 2;
  }

  private boolean isOneOverGamePoint() {
    return this.firstPlayerScore > 3 || this.secondPlayerScore > 3;
  }

  private boolean isBothGamePoint() {
    return this.firstPlayerScore >= 3 && this.secondPlayerScore >= 3;
  }

  private boolean isEven() {
    return this.firstPlayerScore == this.secondPlayerScore;
  }
}
