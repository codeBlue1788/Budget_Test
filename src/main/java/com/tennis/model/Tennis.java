package com.tennis.model;

public class Tennis {

  private String firstPlayerName;

  private String secondPlayerName;

  public Tennis(String firstPlayerName, String secondPlayerName) {
    this.firstPlayerName = firstPlayerName;
    this.secondPlayerName = secondPlayerName;
  }

  private int firstPlayerPoint;

  private int secondPlayerPoint;

  public void firstPlayerScore(){
    this.firstPlayerPoint++;
  }

  public void secondPlayerScore(){
    this.secondPlayerPoint++;
  }

  public String score(){
    return null;
  }

}
